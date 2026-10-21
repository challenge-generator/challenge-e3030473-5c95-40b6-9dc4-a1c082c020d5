package com.empresa.prestamos.infrastructure.adapters.in.mobile;

import com.empresa.prestamos.PrestamosApplication.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/mobile/v1/loans")
public class LoanRequestMobileController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestMobileController.class);
    private static final String CHANNEL_MOBILE = "MOBILE";

    private final LoanRequestUseCase loanRequestUseCase;
    private final Map<String, AtomicLong> requestMetrics = new ConcurrentHashMap<>();

    public LoanRequestMobileController(LoanRequestService loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
        requestMetrics.put("submitted", new AtomicLong(0));
        requestMetrics.put("approved", new AtomicLong(0));
        requestMetrics.put("rejected", new AtomicLong(0));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LoanRequestDTO> submitLoanRequest(@RequestBody LoanRequestDTO requestDTO) {
        log.info("Recibida solicitud de prestamo desde canal MOBILE para cliente: {}", 
                requestDTO.customerId());
        
        requestMetrics.get("submitted").incrementAndGet();
        
        String idempotencyKey = requestDTO.idempotencyKey() != null ? 
                requestDTO.idempotencyKey() : 
                generateMobileIdempotencyKey(requestDTO.customerId());
        
        LoanRequestDTO enrichedDTO = new LoanRequestDTO(
                requestDTO.requestId(),
                requestDTO.customerId(),
                requestDTO.amount(),
                requestDTO.termMonths(),
                requestDTO.requestDate(),
                requestDTO.status(),
                idempotencyKey,
                CHANNEL_MOBILE,
                requestDTO.creditScore(),
                requestDTO.approvedAmount(),
                requestDTO.rejectionReason()
        );

        return loanRequestUseCase.submitRequest(enrichedDTO, CHANNEL_MOBILE)
                .doOnSuccess(result -> {
                    if (result.status() == LoanStatus.APPROVED) {
                        requestMetrics.get("approved").incrementAndGet();
                    } else if (result.status() == LoanStatus.REJECTED) {
                        requestMetrics.get("rejected").incrementAndGet();
                    }
                    log.info("Solicitud procesada desde MOBILE - Estado: {}", result.status());
                })
                .doOnError(error -> log.error("Error procesando solicitud MOBILE: {}", error.getMessage()));
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<LoanRequestDTO> getLoanRequest(@PathVariable String requestId) {
        log.info("Consultando solicitud {} desde canal MOBILE", requestId);
        return loanRequestUseCase.getRequestById(requestId)
                .doOnSuccess(dto -> log.info("Solicitud {} encontrada para canal MOBILE", requestId))
                .doOnError(error -> log.error("Error consultando solicitud MOBILE: {}", error.getMessage()));
    }

    @GetMapping(value = "/stream/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<LoanRequestDTO>> streamLoanStatus() {
        log.info("Iniciando stream de estado de solicitudes para canal MOBILE");
        return loanRequestUseCase.streamPendingRequests()
                .map(dto -> ServerSentEvent.builder(dto)
                        .event("loan-status-update")
                        .build())
                .doOnSubscribe(s -> log.info("Suscriptor conectado al stream MOBILE"))
                .doOnCancel(() -> log.info("Suscriptor desconectado del stream MOBILE"));
    }

    @GetMapping(value = "/metrics", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Long>> getChannelMetrics() {
        log.debug("Consultando metricas del canal MOBILE");
        return Mono.just(Map.of(
                "channel", CHANNEL_MOBILE,
                "submitted", requestMetrics.get("submitted").get(),
                "approved", requestMetrics.get("approved").get(),
                "rejected", requestMetrics.get("rejected").get(),
                "timestamp", Instant.now().toEpochMilli()
        ));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LoanRequestDTO> listLoanRequests(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        log.info("Listando solicitudes para cliente {} con estado {} - pagina {} tamaño {}", 
                customerId, status, page, size);
        return loanRequestUseCase.listRequestsByCustomer(customerId, status, page, size)
                .take(Duration.ofSeconds(30))
                .doOnComplete(() -> log.info("Listado de solicitudes MOBILE completado"));
    }

    private String generateMobileIdempotencyKey(String customerId) {
        return "MOBILE-" + customerId + "-" + System.currentTimeMillis();
    }

    @ExceptionHandler(Exception.class)
    public Mono<Map<String, Object>> handleMobileException(Exception e) {
        log.error("Excepcion en controller MOBILE: {}", e.getMessage(), e);
        return Mono.just(Map.of(
                "error", "Error procesando solicitud mobile",
                "message", e.getMessage(),
                "channel", CHANNEL_MOBILE,
                "timestamp", Instant.now().toString()
        ));
    }
}