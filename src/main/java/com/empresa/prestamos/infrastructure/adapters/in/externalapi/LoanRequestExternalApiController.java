package com.empresa.prestamos.infrastructure.adapters.in.externalapi;

import com.empresa.prestamos.PrestamosApplication.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/api/external/v1/loans")
public class LoanRequestExternalApiController {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestExternalApiController.class);
    private static final String CHANNEL_EXTERNAL = "EXTERNAL_API";

    private final LoanRequestUseCase loanRequestUseCase;
    private final ConcurrentHashMap<String, Long> requestTimestamps = new ConcurrentHashMap<>();
    private final ConcurrentHashMap<String, String> requestTraceIds = new ConcurrentHashMap<>();

    public LoanRequestExternalApiController(LoanRequestService loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> submitExternalLoanRequest(
            @RequestHeader(value = "X-Client-Id", required = false) String clientId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId,
            @RequestBody LoanRequestDTO requestDTO) {
        
        String effectiveTraceId = traceId != null ? traceId : generateTraceId();
        log.info("Solicitud EXTERNAL_API recibida - Client: {}, TraceId: {}", clientId, effectiveTraceId);
        
        requestTraceIds.put(effectiveTraceId, Instant.now().toString());
        
        if (requestDTO.idempotencyKey() == null || requestDTO.idempotencyKey().isBlank()) {
            return Mono.just(ResponseEntity.badRequest().body(Map.of(
                    "error", "Idempotency key es requerido para el canal externo",
                    "code", "MISSING_IDEMPOTENCY_KEY",
                    "traceId", effectiveTraceId
            )));
        }

        LoanRequestDTO enrichedDTO = enrichRequestDTO(requestDTO, clientId, effectiveTraceId);

        return loanRequestUseCase.submitRequest(enrichedDTO, CHANNEL_EXTERNAL)
                .map(result -> {
                    log.info("Solicitud externa procesada exitosamente - TraceId: {}, Status: {}", 
                            effectiveTraceId, result.status());
                    return ResponseEntity.ok(buildSuccessResponse(result, effectiveTraceId));
                })
                .onErrorResume(DuplicateRequestException.class, ex -> {
                    log.warn("Solicitud duplicada detectada - TraceId: {}, Key: {}", 
                            effectiveTraceId, ex.getIdempotencyKey());
                    return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of(
                            "error", "Solicitud duplicada",
                            "idempotencyKey", ex.getIdempotencyKey(),
                            "channel", CHANNEL_EXTERNAL,
                            "traceId", effectiveTraceId,
                            "code", "DUPLICATE_REQUEST"
                    )));
                })
                .onErrorResume(Exception.class, ex -> {
                    log.error("Error procesando solicitud externa - TraceId: {}, Error: {}", 
                            effectiveTraceId, ex.getMessage(), ex);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                            "error", "Error interno procesando solicitud",
                            "message", ex.getMessage(),
                            "traceId", effectiveTraceId,
                            "code", "INTERNAL_ERROR"
                    )));
                });
    }

    @GetMapping(value = "/{requestId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<Object>> getExternalLoanRequest(
            @PathVariable String requestId,
            @RequestHeader(value = "X-Trace-Id", required = false) String traceId) {
        
        String effectiveTraceId = traceId != null ? traceId : generateTraceId();
        log.info("Consultando solicitud externa - RequestId: {}, TraceId: {}", requestId, effectiveTraceId);
        
        return loanRequestUseCase.getRequestById(requestId)
                .map(dto -> ResponseEntity.ok(buildSuccessResponse(dto, effectiveTraceId)))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<LoanRequestDTO> listExternalRequests(
            @RequestParam(required = false) String customerId,
            @RequestParam(required = false) String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        
        log.info("Listando solicitudes externas - Client: {}, Customer: {}, Status: {}", 
                clientId, customerId, status);
        
        return loanRequestUseCase.listRequestsByCustomer(customerId, status, page, size)
                .delayElements(Duration.ofMillis(100))
                .doOnComplete(() -> log.info("Listado de solicitudes externas completado"));
    }

    @PostMapping(value = "/batch", consumes = MediaType.APPLICATION_JSON_VALUE, 
                 produces = MediaType.APPLICATION_JSON_VALUE)
    public Flux<ResponseEntity<Object>> submitBatchRequests(
            @RequestBody Flux<LoanRequestDTO> requests,
            @RequestHeader(value = "X-Client-Id", required = false) String clientId) {
        
        String batchTraceId = generateTraceId();
        log.info("Procesando lote de solicitudes externas - BatchId: {}, Client: {}", 
                batchTraceId, clientId);
        
        return requests
                .flatMap(dto -> loanRequestUseCase.submitRequest(
                        enrichRequestDTO(dto, clientId, batchTraceId), 
                        CHANNEL_EXTERNAL
                ).map(result -> ResponseEntity.ok(buildSuccessResponse(result, batchTraceId))))
                .doOnComplete(() -> log.info("Procesamiento de lote completado - BatchId: {}", batchTraceId));
    }

    @GetMapping(value = "/health", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Map<String, Object>> healthCheck() {
        return Mono.just(Map.of(
                "status", "UP",
                "channel", CHANNEL_EXTERNAL,
                "timestamp", Instant.now().toString(),
                "activeTraces", requestTraceIds.size()
        ));
    }

    private LoanRequestDTO enrichRequestDTO(LoanRequestDTO dto, String clientId, String traceId) {
        String idempotencyKey = dto.idempotencyKey();
        if (idempotencyKey != null && !idempotencyKey.startsWith("EXT-")) {
            idempotencyKey = "EXT-" + clientId + "-" + idempotencyKey;
        }
        return new LoanRequestDTO(
                dto.requestId(),
                dto.customerId(),
                dto.amount(),
                dto.termMonths(),
                dto.requestDate(),
                dto.status(),
                idempotencyKey,
                CHANNEL_EXTERNAL,
                dto.creditScore(),
                dto.approvedAmount(),
                dto.rejectionReason()
        );
    }

    private Map<String, Object> buildSuccessResponse(LoanRequestDTO dto, String traceId) {
        return Map.of(
                "requestId", dto.requestId() != null ? dto.requestId() : "",
                "customerId", dto.customerId(),
                "status", dto.status() != null ? dto.status().name() : "",
                "amount", dto.amount() != null ? dto.amount().toString() : "0",
                "termMonths", dto.termMonths() != null ? dto.termMonths() : 0,
                "channel", CHANNEL_EXTERNAL,
                "traceId", traceId,
                "timestamp", Instant.now().toString()
        );
    }

    private String generateTraceId() {
        return "EXT-" + System.currentTimeMillis() + "-" + 
               java.util.UUID.randomUUID().toString().substring(0, 8);
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleExternalException(Exception e) {
        String traceId = generateTraceId();
        log.error("Excepcion en controller EXTERNAL_API - TraceId: {}, Error: {}", 
                traceId, e.getMessage(), e);
        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "error", "Error en el servicio externo",
                "message", e.getMessage(),
                "traceId", traceId,
                "timestamp", Instant.now().toString()
        )));
    }
}