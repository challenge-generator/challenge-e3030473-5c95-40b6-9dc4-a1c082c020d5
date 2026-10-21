package com.empresa.prestamos.infrastructure.adapters.in.web;


import com.empresa.prestamos.LoanRequestDTO;
import com.empresa.prestamos.application.ports.in.LoanRequestUseCase;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/loans/web")
public class LoanRequestWebController {
    
    private static final Logger log = LoggerFactory.getLogger(LoanRequestWebController.class);
    private static final String CHANNEL_WEB = "WEB";
    
    private final LoanRequestUseCase loanRequestUseCase;
    
    public LoanRequestWebController(LoanRequestUseCase loanRequestUseCase) {
        this.loanRequestUseCase = loanRequestUseCase;
    }
    
    @PostMapping
    public Mono<ResponseEntity<LoanRequestDTO>> submitLoanRequest(
            @RequestBody LoanRequestDTO requestDTO,
            @RequestHeader(value = "X-Idempotency-Key", required = false) String idempotencyKey,
            @RequestHeader(value = "X-Request-Id", required = false) String requestId) {
        
        log.info("Solicitud recibida via WEB para cliente: {}", requestDTO.customerId());
        
        LoanRequest loanRequest = new LoanRequest(
            requestId != null ? UUID.fromString(requestId) : UUID.randomUUID(),
            requestDTO.customerId(),
            requestDTO.amount(),
            requestDTO.termMonths(),
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey
        );
        
        return loanRequestUseCase.submitRequest(loanRequest, CHANNEL_WEB)
            .map(this::toResponseDTO)
            .map(ResponseEntity::ok)
            .onErrorResume(DuplicateRequestException.class, error -> {
                log.warn("Solicitud duplicada via WEB - clave: {}, canal: {}", 
                    error.getIdempotencyKey(), error.getChannel());
                return Mono.just(ResponseEntity.status(HttpStatus.CONFLICT)
                    .<LoanRequestDTO>body(null));
            })
            .onErrorResume(IllegalArgumentException.class, error -> {
                log.error("Validacion fallida via WEB: {}", error.getMessage());
                return Mono.just(ResponseEntity.badRequest()
                    .<LoanRequestDTO>body(null));
            })
            .onErrorResume(Exception.class, error -> {
                log.error("Error interno procesando solicitud WEB: {}", error.getMessage(), error);
                return Mono.just(ResponseEntity.internalServerError()
                    .<LoanRequestDTO>body(null));
            });
    }
    
    private LoanRequestDTO toResponseDTO(LoanRequest request) {
        return new LoanRequestDTO(
            request.getRequestId().toString(),
            request.getCustomerId(),
            request.getAmount(),
            request.getTermMonths(),
            request.getRequestDate().toString(),
            request.getStatus().name(),
            request.getIdempotencyKey()
        );
    }
}