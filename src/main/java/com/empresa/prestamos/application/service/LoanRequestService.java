package com.empresa.prestamos.application.service;

import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.scheduler.Schedulers;

public class LoanRequestService implements LoanRequestUseCase {
    
    private static final Logger log = LoggerFactory.getLogger(LoanRequestService.class);
    private static final BigDecimal MIN_AMOUNT = new BigDecimal("1000");
    private static final BigDecimal MAX_AMOUNT = new BigDecimal("50000");
    private static final int MIN_TERM_MONTHS = 6;
    private static final int MAX_TERM_MONTHS = 48;
    private static final int MAX_CONCURRENT_REQUESTS = 100;
    
    private final LoanRequestPersistencePort persistencePort;
    private final CreditCheckServicePort creditCheckServicePort;
    private final Sinks.Many<LoanRequest> requestProcessingSink;
    private volatile int currentBackpressureRequests = 0;
    private final Object backpressureLock = new Object();
    
    public LoanRequestService(
            LoanRequestPersistencePort persistencePort,
            CreditCheckServicePort creditCheckServicePort) {
        this.persistencePort = persistencePort;
        this.creditCheckServicePort = creditCheckServicePort;
        this.requestProcessingSink = Sinks.many().multicast().directBestEffort();
    }
    
    @Override
    public Mono<LoanRequest> submitRequest(LoanRequest request, String channel) {
        log.info("Recibida solicitud de prestamo para cliente: {}, canal: {}", 
                request.getCustomerId(), channel);
        
        validateRequest(request);
        
        IdempotencyKey idempotencyKey = new IdempotencyKey(
            request.getIdempotencyKey() != null ? request.getIdempotencyKey() : UUID.randomUUID().toString(),
            channel
        );
        
        return checkBackpressure()
            .then(checkIdempotency(idempotencyKey))
            .then(persistInitialRequest(request, idempotencyKey))
            .flatMap(this::processCreditCheck)
            .doOnSuccess(result -> log.info("Solicitud procesada exitosamente: {}", result.getRequestId()))
            .doOnError(error -> log.error("Error procesar solicitud: {}", error.getMessage()));
    }
    
    private Mono<Void> checkBackpressure() {
        return Mono.fromRunnable(() -> {
            synchronized (backpressureLock) {
                if (currentBackpressureRequests >= MAX_CONCURRENT_REQUESTS) {
                    log.warn("Backpressure detectado. Solicitudes en proceso: {}", currentBackpressureRequests);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
                currentBackpressureRequests++;
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }
    
    private Mono<Void> checkIdempotency(IdempotencyKey idempotencyKey) {
        return persistencePort.findByIdempotencyKey(idempotencyKey.getCompositeKey())
            .flatMap(existing -> {
                log.warn("Solicitud duplicada detectada para clave: {}", idempotencyKey.getCompositeKey());
                return Mono.error(new DuplicateRequestException(
                    idempotencyKey.getKey(), 
                    idempotencyKey.getChannel()
                ));
            })
            .then();
    }
    
    private Mono<LoanRequest> persistInitialRequest(LoanRequest request, IdempotencyKey idempotencyKey) {
        LoanRequest requestWithKey = new LoanRequest(
            request.getRequestId() != null ? request.getRequestId() : UUID.randomUUID(),
            request.getCustomerId(),
            request.getAmount(),
            request.getTermMonths(),
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey.getCompositeKey()
        );
        
        return persistencePort.save(requestWithKey)
            .doOnSuccess(saved -> {
                log.info("Solicitud persistida con ID: {}, clave idempotencia: {}", 
                    saved.getRequestId(), saved.getIdempotencyKey());
                requestProcessingSink.emitNext(saved, Sinks.EmitFailureHandler.FAIL_FAST);
            });
    }
    
    private Mono<LoanRequest> processCreditCheck(LoanRequest request) {
        return creditCheckServicePort.checkCreditHistory(
            request.getCustomerId(),
            request.getAmount()
        )
        .flatMap(creditResult -> {
            if (creditResult.approved()) {
                LoanRequest approvedRequest = request.withStatus(LoanStatus.APPROVED);
                return persistencePort.save(approvedRequest)
                    .doOnSuccess(saved -> log.info("Prestamo aprobado para cliente: {}, monto: {}", 
                        saved.getCustomerId(), saved.getAmount()));
            } else {
                LoanRequest rejectedRequest = request.withStatus(LoanStatus.REJECTED);
                return persistencePort.save(rejectedRequest)
                    .doOnSuccess(saved -> log.info("Prestamo rechazado para cliente: {}, razon: {}", 
                        saved.getCustomerId(), creditResult.rejectionReason()));
            }
        })
        .doFinally(signalType -> {
            synchronized (backpressureLock) {
                currentBackpressureRequests--;
            }
            log.debug("Procesamiento de solicitud completado, solicitudes en proceso: {}", 
                currentBackpressureRequests);
        });
    }
    
    private void validateRequest(LoanRequest request) {
        if (request.getCustomerId() == null || request.getCustomerId().isBlank()) {
            throw new IllegalArgumentException("El ID del cliente es requerido");
        }
        if (request.getAmount() == null) {
            throw new IllegalArgumentException("El monto del prestamo es requerido");
        }
        if (request.getAmount().compareTo(MIN_AMOUNT) < 0) {
            throw new IllegalArgumentException(
                "El monto minimo del prestamo es: " + MIN_AMOUNT);
        }
        if (request.getAmount().compareTo(MAX_AMOUNT) > 0) {
            throw new IllegalArgumentException(
                "El monto maximo del prestamo es: " + MAX_AMOUNT);
        }
        if (request.getTermMonths() == null) {
            throw new IllegalArgumentException("El plazo en meses es requerido");
        }
        if (request.getTermMonths() < MIN_TERM_MONTHS || request.getTermMonths() > MAX_TERM_MONTHS) {
            throw new IllegalArgumentException(
                "El plazo debe estar entre " + MIN_TERM_MONTHS + " y " + MAX_TERM_MONTHS + " meses");
        }
    }
    
    public Sinks.Many<LoanRequest> getRequestProcessingSink() {
        return requestProcessingSink;
    }
    
    public int getCurrentBackpressureRequests() {
        return currentBackpressureRequests;
    }
}