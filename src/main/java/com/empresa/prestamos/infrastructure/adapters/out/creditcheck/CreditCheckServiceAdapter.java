package com.empresa.prestamos.infrastructure.adapters.out.creditcheck;

import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.domain.exception.CreditCheckServiceException;
import com.empresa.prestamos.domain.model.LoanRequest;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class CreditCheckServiceAdapter implements CreditCheckServicePort {

    private static final Logger log = LoggerFactory.getLogger(CreditCheckServiceAdapter.class);
    private static final int MINIMUM_SCORE_FOR_APPROVAL = 650;

    private final WebClient webClient;
    private final String creditCheckBaseUrl;
    private final int timeoutSeconds;
    private final int maxRetries;
    private final long retryDelayMillis;

    private final Map<String, CreditCheckCacheEntry> cache = new ConcurrentHashMap<>();

    public CreditCheckServiceAdapter(
            @Value("${external.credit-check.base-url:https://api.credit bureau.com/v1}") String creditCheckBaseUrl,
            @Value("${external.credit-check.timeout-seconds:5}") int timeoutSeconds,
            @Value("${external.credit-check.max-retries:3}") int maxRetries,
            @Value("${external.credit-check.retry-delay-millis:1000}") long retryDelayMillis) {
        this.creditCheckBaseUrl = creditCheckBaseUrl;
        this.timeoutSeconds = timeoutSeconds;
        this.maxRetries = maxRetries;
        this.retryDelayMillis = retryDelayMillis;
        this.webClient = WebClient.builder()
                .baseUrl(creditCheckBaseUrl)
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .build();
    }

    @Override
    @CircuitBreaker(name = "creditCheckCircuitBreaker", fallbackMethod = "fallbackCreditCheck")
    public Mono<CreditCheckResult> checkCredit(LoanRequest request) {
        String cacheKey = buildCacheKey(request.getCustomerId());

        CreditCheckCacheEntry cached = cache.get(cacheKey);
        if (cached != null && !cached.isExpired()) {
            log.info("Credit check cache hit for customer: {}", request.getCustomerId());
            return Mono.just(cached.getResult());
        }

        log.info("Executing credit check for customer: {}, requestId: {}", 
                request.getCustomerId(), request.getRequestId());

        return webClient.post()
                .uri("/credit-assessment")
                .bodyValue(buildCreditCheckRequest(request))
                .retrieve()
                .bodyToMono(CreditCheckResponse.class)
                .timeout(Duration.ofSeconds(timeoutSeconds))
                .retryWhen(Retry.backoff(maxRetries, Duration.ofMillis(retryDelayMillis))
                        .filter(throwable -> isRetryable(throwable))
                        .doBeforeRetry(signal -> {
                            log.warn("Retrying credit check for customer: {}, attempt: {}",
                                    request.getCustomerId(), signal.totalRetries() + 1);
                        }))
                .map(this::mapToResult)
                .doOnSuccess(result -> {
                    cache.put(cacheKey, new CreditCheckCacheEntry(result, Duration.ofMinutes(15)));
                    log.info("Credit check completed for customer: {}, score: {}", 
                            request.getCustomerId(), result.score());
                })
                .doOnError(error -> log.error("Credit check failed for customer: {}, error: {}",
                        request.getCustomerId(), error.getMessage()));
    }

    private boolean isRetryable(Throwable throwable) {
        return throwable instanceof org.springframework.web.reactive.function.client.WebClientResponseException.ServiceUnavailable
                || throwable instanceof java.util.concurrent.TimeoutException
                || throwable instanceof java.io.IOException;
    }

    private CreditCheckRequest buildCreditCheckRequest(LoanRequest request) {
        return new CreditCheckRequest(
                request.getCustomerId(),
                request.getAmount(),
                request.getTermMonths()
        );
    }

    private CreditCheckResult mapToResult(CreditCheckResponse response) {
        boolean approved = response.score() >= MINIMUM_SCORE_FOR_APPROVAL;
        String reason = approved ? "Credit score meets minimum requirement" 
                : "Credit score below minimum requirement of " + MINIMUM_SCORE_FOR_APPROVAL;
        return new CreditCheckResult(response.score(), approved, response.reference(), reason);
    }

    private String buildCacheKey(String customerId) {
        return "credit-check:" + customerId;
    }

    @SuppressWarnings("unused")
    private Mono<CreditCheckResult> fallbackCreditCheck(LoanRequest request, Throwable throwable) {
        log.error("Circuit breaker triggered for credit check. Customer: {}, Error: {}",
                request.getCustomerId(), throwable.getMessage());
        throw new CreditCheckServiceException(
                "Credit check service unavailable. Please try again later.",
                request.getCustomerId(),
                throwable
        );
    }

    public record CreditCheckRequest(String customerId, java.math.BigDecimal amount, Integer termMonths) {}

    public record CreditCheckResponse(Integer score, String reference, String details) {}

    public record CreditCheckResult(Integer score, boolean approved, String reference, String reason) {}

    private static class CreditCheckCacheEntry {
        private final CreditCheckResult result;
        private final long expiresAt;

        CreditCheckCacheEntry(CreditCheckResult result, Duration ttl) {
            this.result = result;
            this.expiresAt = System.currentTimeMillis() + ttl.toMillis();
        }

        CreditCheckResult getResult() {
            return result;
        }

        boolean isExpired() {
            return System.currentTimeMillis() > expiresAt;
        }
    }
}