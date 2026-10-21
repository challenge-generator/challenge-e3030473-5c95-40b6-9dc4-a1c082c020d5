package com.empresa.prestamos.infrastructure.adapters.out.persistence;

import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "loan_requests", indexes = {
    @Index(name = "idx_customer_id", columnList = "customerId"),
    @Index(name = "idx_idempotency_key", columnList = "idempotencyKey", unique = true),
    @Index(name = "idx_status", columnList = "status")
})
public class LoanRequestEntity {

    @Id
    @Column(name = "request_id", nullable = false)
    private UUID requestId;

    @Column(name = "customer_id", nullable = false, length = 50)
    private String customerId;

    @Column(name = "amount", nullable = false, precision = 19, scale = 4)
    private BigDecimal amount;

    @Column(name = "term_months", nullable = false)
    private Integer termMonths;

    @Column(name = "request_date", nullable = false)
    private LocalDate requestDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private LoanStatus status;

    @Column(name = "idempotency_key", nullable = false, unique = true, length = 100)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @Column(name = "credit_check_score")
    private Integer creditCheckScore;

    @Column(name = "credit_check_reference", length = 100)
    private String creditCheckReference;

    @Column(name = "rejection_reason", length = 500)
    private String rejectionReason;

    public LoanRequestEntity() {
    }

    public static LoanRequestEntity fromDomain(LoanRequest loanRequest) {
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.requestId = loanRequest.getRequestId();
        entity.customerId = loanRequest.getCustomerId();
        entity.amount = loanRequest.getAmount();
        entity.termMonths = loanRequest.getTermMonths();
        entity.requestDate = loanRequest.getRequestDate();
        entity.status = loanRequest.getStatus();
        entity.idempotencyKey = loanRequest.getIdempotencyKey();
        entity.createdAt = LocalDate.now();
        entity.updatedAt = LocalDate.now();
        return entity;
    }

    public LoanRequest toDomain() {
        return new LoanRequest(
            this.requestId,
            this.customerId,
            this.amount,
            this.termMonths,
            this.requestDate,
            this.status,
            this.idempotencyKey
        );
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTermMonths() {
        return termMonths;
    }

    public void setTermMonths(Integer termMonths) {
        this.termMonths = termMonths;
    }

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public void setIdempotencyKey(String idempotencyKey) {
        this.idempotencyKey = idempotencyKey;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Integer getCreditCheckScore() {
        return creditCheckScore;
    }

    public void setCreditCheckScore(Integer creditCheckScore) {
        this.creditCheckScore = creditCheckScore;
    }

    public String getCreditCheckReference() {
        return creditCheckReference;
    }

    public void setCreditCheckReference(String creditCheckReference) {
        this.creditCheckReference = creditCheckReference;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }
}