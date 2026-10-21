package com.empresa.prestamos.application.service;

import com.empresa.prestamos.application.ports.out.CreditCheckServicePort;
import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Pruebas unitarias para LoanRequestService")
class LoanRequestServiceTest {

    @Mock
    private LoanRequestPersistencePort persistencePort;

    @Mock
    private CreditCheckServicePort creditCheckServicePort;

    @InjectMocks
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private IdempotencyKey idempotencyKey;

    @BeforeEach
    void setUp() {
        UUID requestId = UUID.randomUUID();
        String customerId = "CUST-001";
        BigDecimal amount = new BigDecimal("50000.00");
        Integer termMonths = 24;

        idempotencyKey = new IdempotencyKey(customerId + "|" + amount.toPlainString(), "WEB");

        loanRequest = new LoanRequest(
            requestId,
            customerId,
            amount,
            termMonths,
            LocalDate.now(),
            LoanStatus.PENDING,
            idempotencyKey.getCompositeKey()
        );
    }

    @Test
    @DisplayName("Debe aprobar solicitud cuando el servicio de crédito responde positivamente")
    void shouldApproveRequestWhenCreditCheckPasses() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString())).thenReturn(Mono.just(true));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.APPROVED, savedRequest.getStatus());
                assertNotNull(savedRequest.getRequestId());
            })
            .verifyComplete();

        verify(persistencePort).existsByIdempotencyKey(idempotencyKey.getCompositeKey());
        verify(creditCheckServicePort).checkCreditHistory(loanRequest.getCustomerId());
        verify(persistencePort).save(any(LoanRequest.class));
    }

    @Test
    @DisplayName("Debe rechazar solicitud cuando el servicio de crédito responde negativamente")
    void shouldRejectRequestWhenCreditCheckFails() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString())).thenReturn(Mono.just(false));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.REJECTED, savedRequest.getStatus());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe lanzar DuplicateRequestException cuando la clave de idempotencia ya existe")
    void shouldThrowDuplicateRequestExceptionWhenIdempotencyKeyExists() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(true));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .expectError(DuplicateRequestException.class)
            .verify();

        verify(persistencePort, never()).save(any(LoanRequest.class));
        verify(creditCheckServicePort, never()).checkCreditHistory(anyString());
    }

    @Test
    @DisplayName("Debe manejar backpressure cuando el buffer está lleno")
    void shouldHandleBackpressureWhenBufferIsFull() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString()))
            .thenReturn(Mono.delay(java.time.Duration.ofMillis(100)).flatMap(l -> Mono.just(true)));
        when(persistencePort.save(any(LoanRequest.class)))
            .thenReturn(Mono.delay(java.time.Duration.ofMillis(50)).flatMap(l -> Mono.just(loanRequest)));

        int concurrentRequests = 100;
        var requests = java.util.stream.IntStream.range(0, concurrentRequests)
            .mapToObj(i -> loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .toList();

        StepVerifier.create(Mono.zip(requests, objects -> objects))
            .expectComplete()
            .verify(java.time.Duration.ofSeconds(10));
    }

    @Test
    @DisplayName("Debe recuperar automáticamente cuando el servicio de crédito falla temporalmente")
    void shouldRecoverAutomaticallyWhenCreditServiceFailsTemporarily() {
        when(persistencePort.existsByIdempotencyKey(anyString())).thenReturn(Mono.just(false));
        when(creditCheckServicePort.checkCreditHistory(anyString()))
            .thenReturn(Mono.error(new RuntimeException("Servicio no disponible")))
            .thenReturn(Mono.just(true));
        when(persistencePort.save(any(LoanRequest.class))).thenAnswer(inv -> Mono.just(inv.getArgument(0)));

        StepVerifier.create(loanRequestService.processLoanRequest(loanRequest, idempotencyKey))
            .assertNext(savedRequest -> {
                assertEquals(LoanStatus.APPROVED, savedRequest.getStatus());
            })
            .verifyComplete();
    }

    @Test
    @DisplayName("Debe rechazar solicitud con monto inválido")
    void shouldRejectRequestWithInvalidAmount() {
        LoanRequest invalidRequest = new LoanRequest(
            UUID.randomUUID(),
            "CUST-001",
            new BigDecimal("-1000.00"),
            24,
            LocalDate.now(),
            LoanStatus.PENDING,
            "invalid-key"
        );

        StepVerifier.create(loanRequestService.processLoanRequest(invalidRequest, idempotencyKey))
            .expectError(IllegalArgumentException.class)
            .verify();
    }

    @Test
    @DisplayName("Debe rechazar solicitud con plazo inválido")
    void shouldRejectRequestWithInvalidTerm() {
        LoanRequest invalidRequest = new LoanRequest(
            UUID.randomUUID(),
            "CUST-001",
            new BigDecimal("50000.00"),
            0,
            LocalDate.now(),
            LoanStatus.PENDING,
            "invalid-key"
        );

        StepVerifier.create(loanRequestService.processLoanRequest(invalidRequest, idempotencyKey))
            .expectError(IllegalArgumentException.class)
            .verify();
    }
}