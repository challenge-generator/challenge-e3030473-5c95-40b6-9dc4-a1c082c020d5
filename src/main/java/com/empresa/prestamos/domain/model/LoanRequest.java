package com.empresa.prestamos.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio que representa una solicitud de préstamo.
 * Contiene los atributos esenciales para la evaluación crediticia y
 * la lógica de negocio asociada.
 */
public final class LoanRequest {
    private final UUID requestId;
    private final String customerId;
    private final BigDecimal amount;
    private final Integer termMonths;
    private final LocalDate requestDate;
    private final LoanStatus status;
    private final String idempotencyKey;

    public LoanRequest(UUID requestId, String customerId, BigDecimal amount,
                      Integer termMonths, LocalDate requestDate, LoanStatus status,
                      String idempotencyKey) {
        this.requestId = Objects.requireNonNull(requestId, "requestId no puede ser nulo");
        this.customerId = Objects.requireNonNull(customerId, "customerId no puede ser nulo");
        this.amount = Objects.requireNonNull(amount, "amount no puede ser nulo");
        this.termMonths = Objects.requireNonNull(termMonths, "termMonths no puede ser nulo");
        this.requestDate = Objects.requireNonNull(requestDate, "requestDate no puede ser nulo");
        this.status = Objects.requireNonNull(status, "status no puede ser nulo");
        this.idempotencyKey = Objects.requireNonNull(idempotencyKey, "idempotencyKey no puede ser nulo");
        
        validateAmount();
        validateTerm();
    }

    private void validateAmount() {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto del préstamo debe ser mayor que cero");
        }
    }

    private void validateTerm() {
        if (termMonths <= 0 || termMonths > 84) {
            throw new IllegalArgumentException("El plazo en meses debe estar entre 1 y 84");
        }
    }

    public UUID getRequestId() {
        return requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public LoanRequest withStatus(LoanStatus newStatus) {
        return new LoanRequest(this.requestId, this.customerId, this.amount,
                              this.termMonths, this.requestDate, newStatus, this.idempotencyKey);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoanRequest that = (LoanRequest) o;
        return requestId.equals(that.requestId) && 
               customerId.equals(that.customerId) && 
               amount.equals(that.amount) && 
               termMonths.equals(that.termMonths) && 
               requestDate.equals(that.requestDate) && 
               status == that.status && 
               idempotencyKey.equals(that.idempotencyKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, customerId, amount, termMonths, requestDate, status, idempotencyKey);
    }

    @Override
    public String toString() {
        return "LoanRequest{" +
                "requestId=" + requestId +
                ", customerId='" + customerId + '\'' +
                ", amount=" + amount +
                ", termMonths=" + termMonths +
                ", requestDate=" + requestDate +
                ", status=" + status +
                ", idempotencyKey='" + idempotencyKey + '\'' +
                '}';
    }

    public enum LoanStatus {
        PENDING,
        APPROVED,
        REJECTED,
        FAILED
    }
}