package com.empresa.prestamos.application.ports.out;

import com.empresa.prestamos.domain.model.IdempotencyKey;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface LoanRequestPersistencePort {

    Mono<LoanRequest> save(LoanRequest loanRequest);

    Mono<LoanRequest> findById(UUID requestId);

    Mono<LoanRequest> findByIdempotencyKey(String idempotencyKey);

    Mono<LoanRequest> findByCustomerId(String customerId);

    Mono<List<LoanRequest>> findByCustomerIdAndStatus(String customerId, LoanStatus status);

    Mono<List<LoanRequest>> findByStatus(LoanStatus status);

    Mono<List<LoanRequest>> findByDateRange(LocalDate startDate, LocalDate endDate);

    Mono<LoanRequest> update(LoanRequest loanRequest);

    Mono<Boolean> existsByIdempotencyKey(String idempotencyKey);

    Mono<Boolean> existsById(UUID requestId);

    Mono<Void> deleteById(UUID requestId);

    Flux<LoanRequest> findAll();

    Mono<Long> count();

    Mono<Long> countByStatus(LoanStatus status);

    Mono<LoanRequest> findByIdempotencyKeyAndChannel(String idempotencyKey, String channel);

    Mono<LoanRequest> saveWithIdempotencyCheck(LoanRequest loanRequest, IdempotencyKey idempotencyKey);
}