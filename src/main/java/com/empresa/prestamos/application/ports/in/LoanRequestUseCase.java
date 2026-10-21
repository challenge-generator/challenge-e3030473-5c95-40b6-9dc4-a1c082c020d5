package com.empresa.prestamos.application.ports.in;

import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public interface LoanRequestUseCase {

    Mono<LoanRequest> submitLoanRequest(String customerId, BigDecimal amount, 
                                         Integer termMonths, String idempotencyKey);

    Mono<LoanRequest> getLoanRequestById(UUID requestId);

    Mono<LoanRequest> getLoanRequestByIdempotencyKey(String idempotencyKey);

    Mono<LoanRequest> approveLoanRequest(UUID requestId);

    Mono<LoanRequest> rejectLoanRequest(UUID requestId, String rejectionReason);

    Mono<LoanRequest> updateLoanRequestStatus(UUID requestId, LoanStatus newStatus);

    Mono<Boolean> validateCustomerEligibility(String customerId);

    Mono<LoanRequest> reprocessLoanRequest(UUID requestId);
}