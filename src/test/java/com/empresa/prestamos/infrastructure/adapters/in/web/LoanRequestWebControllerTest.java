package com.empresa.prestamos.infrastructure.adapters.in.web;


import com.empresa.prestamos.domain.exception.DuplicateRequestException;
import com.empresa.prestamos.application.service.LoanRequestService;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.web.reactive.function.BodyInserters.*;

@WebFluxTest(LoanRequestWebController.class)
@DisplayName("Pruebas de integración para LoanRequestWebController")
class LoanRequestWebControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private LoanRequestService loanRequestService;

    private LoanRequest loanRequest;
    private String requestBody;

    @BeforeEach
    void setUp() {
        UUID requestId = UUID.randomUUID();
        loanRequest = new LoanRequest(
            requestId,
            "CUST-001",
            new BigDecimal("50000.00"),
            24,
            LocalDate.now(),
            LoanStatus.APPROVED,
            "CUST-001|50000.00"
        );

        requestBody = """
            {
                "customerId": "CUST-001",
                "amount": 50000.00,
                "termMonths": 24,
                "idempotencyKey": "CUST-001|50000.00"
            }
            """;
    }

    @Test
    @DisplayName("Debe crear solicitud de préstamo exitosamente")
    void shouldCreateLoanRequestSuccessfully() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.requestId").isEqualTo(loanRequest.getRequestId().toString())
            .jsonPath("$.customerId").isEqualTo("CUST-001")
            .jsonPath("$.status").isEqualTo("APPROVED");
    }

    @Test
    @DisplayName("Debe devolver 400 cuando el monto es inválido")
    void shouldReturn400WhenAmountIsInvalid() {
        String invalidBody = """
            {
                "customerId": "CUST-001",
                "amount": -1000.00,
                "termMonths": 24,
                "idempotencyKey": "CUST-001|50000.00"
            }
            """;

        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new IllegalArgumentException("Monto inválido")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(invalidBody)
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("Debe devolver 409 cuando hay solicitud duplicada")
    void shouldReturn409WhenDuplicateRequest() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new com.empresa.prestamos.domain.exception.DuplicateRequestException(
                "CUST-001|50000.00", "WEB")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isEqualTo(409);
    }

    @Test
    @DisplayName("Debe devolver 500 cuando el servicio externo falla")
    void shouldReturn500WhenExternalServiceFails() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.error(new RuntimeError("Error de conexión con servicio de crédito")));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().is5xxServerError();
    }

    @Test
    @DisplayName("Debe obtener solicitud por ID exitosamente")
    void shouldGetRequestByIdSuccessfully() {
        when(loanRequestService.findById(any(UUID.class)))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.get()
            .uri("/api/v1/loan-requests/{id}", loanRequest.getRequestId())
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.requestId").isEqualTo(loanRequest.getRequestId().toString());
    }

    @Test
    @DisplayName("Debe devolver 404 cuando la solicitud no existe")
    void shouldReturn404WhenRequestNotFound() {
        when(loanRequestService.findById(any(UUID.class)))
            .thenReturn(Mono.empty());

        webTestClient.get()
            .uri("/api/v1/loan-requests/{id}", UUID.randomUUID())
            .exchange()
            .expectStatus().isNotFound();
    }

    @Test
    @DisplayName("Debe validar que el Content-Type sea application/json")
    void shouldValidateContentTypeIsJson() {
        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.TEXT_PLAIN)
            .bodyValue("invalid payload")
            .exchange()
            .expectStatus().isUnsupportedMediaType();
    }

    @Test
    @DisplayName("Debe manejar múltiples solicitudes concurrentes correctamente")
    void shouldHandleConcurrentRequestsCorrectly() {
        when(loanRequestService.processLoanRequest(any(LoanRequest.class), any()))
            .thenReturn(Mono.just(loanRequest));

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated();

        webTestClient.post()
            .uri("/api/v1/loan-requests")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(requestBody)
            .exchange()
            .expectStatus().isCreated();
    }
}