package com.empresa.prestamos;


import com.empresa.prestamos.infrastructure.adapters.out.creditcheck.CreditCheckServiceAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.scheduler.Schedulers;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableWebFlux
@EnableAsync
public class PrestamosApplication {
    
    private final IdempotencyManager idempotencyManager;
    private final LoanRequestProcessor loanRequestProcessor;
    private final CreditCheckServiceAdapter creditCheckServiceAdapter;
    
    public PrestamosApplication(IdempotencyManager idempotencyManager,
                                LoanRequestProcessor loanRequestProcessor,
                                CreditCheckServiceAdapter creditCheckServiceAdapter) {
        this.idempotencyManager = idempotencyManager;
        this.loanRequestProcessor = loanRequestProcessor;
        this.creditCheckServiceAdapter = creditCheckServiceAdapter;
        
        validateDependencies();
    }
    
    private void validateDependencies() {
        if (idempotencyManager == null) {
            throw new IllegalStateException("IdempotencyManager no puede ser null");
        }
        if (loanRequestProcessor == null) {
            throw new IllegalStateException("LoanRequestProcessor no puede ser null");
        }
        if (creditCheckServiceAdapter == null) {
            throw new IllegalStateException("CreditCheckServiceAdapter no puede ser null");
        }
    }
    
    @Bean
    public Executor taskExecutor() {
        return Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    }
    
    @Bean
    public reactor.core.scheduler.Scheduler reactorScheduler() {
        return Schedulers.newBoundedElastic(
            Runtime.getRuntime().availableProcessors() * 2,
            Integer.MAX_VALUE,
            "reactor-pool"
        );
    }
    
    public static void main(String[] args) {
        SpringApplication.run(PrestamosApplication.class, args);
    }
    
    // Clase interna para manejar la idempotencia
    public static class IdempotencyManager {
        private final java.util.Map<String, Boolean> idempotencyCache;
        
        public IdempotencyManager() {
            this.idempotencyCache = new java.util.concurrent.ConcurrentHashMap<>();
        }
        
        public boolean isIdempotent(String requestId) {
            if (requestId == null || requestId.trim().isEmpty()) {
                throw new IllegalArgumentException("requestId no puede ser null o vacio");
            }
            return idempotencyCache.putIfAbsent(requestId, Boolean.TRUE) != null;
        }
        
        public void clear(String requestId) {
            idempotencyCache.remove(requestId);
        }
    }
    
    // Clase interna para procesar solicitudes de préstamo
    public static class LoanRequestProcessor {
        private final reactor.core.publisher.Sinks.Many<LoanRequestDTO> requestSink;
        private final reactor.core.publisher.Flux<LoanRequestDTO> requestFlux;
        
        public LoanRequestProcessor() {
            this.requestSink = reactor.core.publisher.Sinks.many().multicast().onBackpressureBuffer();
            this.requestFlux = requestSink.asFlux();
        }
        
        public void processRequest(LoanRequestDTO request) {
            if (request == null) {
                throw new IllegalArgumentException("La solicitud no puede ser null");
            }
            requestSink.tryEmitNext(request);
        }
        
        public reactor.core.publisher.Flux<LoanRequestDTO> getRequestFlux() {
            return requestFlux;
        }
    }
    
    // DTO básico para la solicitud de préstamo
    public record LoanRequestDTO(
        String requestId,
        String customerId,
        double amount,
        int termMonths,
        String channel
    ) {
        public LoanRequestDTO {
            if (requestId == null || requestId.trim().isEmpty()) {
                throw new IllegalArgumentException("requestId es obligatorio");
            }
            if (customerId == null || customerId.trim().isEmpty()) {
                throw new IllegalArgumentException("customerId es obligatorio");
            }
            if (amount <= 0) {
                throw new IllegalArgumentException("amount debe ser positivo");
            }
            if (termMonths <= 0) {
                throw new IllegalArgumentException("termMonths debe ser positivo");
            }
            if (channel == null || channel.trim().isEmpty()) {
                throw new IllegalArgumentException("channel es obligatorio");
            }
        }
    }
}