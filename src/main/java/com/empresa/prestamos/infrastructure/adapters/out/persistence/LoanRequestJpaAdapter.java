package com.empresa.prestamos.infrastructure.adapters.out.persistence;

import com.empresa.prestamos.application.ports.out.LoanRequestPersistencePort;
import com.empresa.prestamos.domain.model.LoanRequest;
import com.empresa.prestamos.domain.model.LoanRequest.LoanStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LoanRequestJpaAdapter implements LoanRequestPersistencePort {

    private static final Logger log = LoggerFactory.getLogger(LoanRequestJpaAdapter.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Mono<LoanRequest> save(LoanRequest loanRequest) {
        log.debug("Persistiendo solicitud de prestamo: {}", loanRequest.getRequestId());
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = toEntity(loanRequest);
            entityManager.persist(entity);
            entityManager.flush();
            log.info("Solicitud {} persistida exitosamente en canal {}", 
                    loanRequest.getRequestId(), loanRequest.getIdempotencyKey());
            return toDomain(entity);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<LoanRequest> findById(UUID requestId) {
        log.debug("Buscando solicitud por ID: {}", requestId);
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            return entity != null ? toDomain(entity) : null;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<LoanRequest> findByIdempotencyKey(String idempotencyKey, String channel) {
        log.debug("Buscando solicitud por clave de idempotencia: {} en canal {}", idempotencyKey, channel);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.idempotencyKey = :key AND e.channel = :channel",
                    LoanRequestEntity.class);
            query.setParameter("key", idempotencyKey);
            query.setParameter("channel", channel);
            query.setMaxResults(1);
            
            List<LoanRequestEntity> results = query.getResultList();
            return results.isEmpty() ? null : toDomain(results.get(0));
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByCustomerId(String customerId) {
        log.debug("Buscando solicitudes para cliente: {}", customerId);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.customerId = :customerId ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("customerId", customerId);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByStatus(LoanStatus status) {
        log.debug("Buscando solicitudes con estado: {}", status);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.status = :status ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("status", status.name());
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findByCustomerIdAndStatus(String customerId, LoanStatus status) {
        log.debug("Buscando solicitudes para cliente {} con estado {}", customerId, status);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.customerId = :customerId AND e.status = :status " +
                    "ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setParameter("customerId", customerId);
            query.setParameter("status", status.name());
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<LoanRequest> updateStatus(UUID requestId, LoanStatus newStatus) {
        log.info("Actualizando estado de solicitud {} a {}", requestId, newStatus);
        return Mono.fromCallable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            if (entity == null) {
                throw new IllegalArgumentException("Solicitud no encontrada: " + requestId);
            }
            entity.setStatus(newStatus.name());
            entity.setLastUpdated(java.time.Instant.now());
            entityManager.flush();
            log.info("Estado de solicitud {} actualizado a {}", requestId, newStatus);
            return toDomain(entity);
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Long> countByCustomerId(String customerId) {
        log.debug("Contando solicitudes para cliente: {}", customerId);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT COUNT(e) FROM LoanRequestEntity e WHERE e.customerId = :customerId");
            query.setParameter("customerId", customerId);
            return (Long) query.getSingleResult();
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Mono<Boolean> existsByIdempotencyKey(String idempotencyKey, String channel) {
        log.debug("Verificando existencia de clave de idempotencia: {} en canal {}", idempotencyKey, channel);
        return Mono.fromCallable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT COUNT(e) FROM LoanRequestEntity e WHERE e.idempotencyKey = :key AND e.channel = :channel");
            query.setParameter("key", idempotencyKey);
            query.setParameter("channel", channel);
            Long count = (Long) query.getSingleResult();
            return count > 0;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    @Transactional
    public Mono<Void> deleteById(UUID requestId) {
        log.info("Eliminando solicitud: {}", requestId);
        return Mono.fromRunnable(() -> {
            LoanRequestEntity entity = entityManager.find(LoanRequestEntity.class, requestId);
            if (entity != null) {
                entityManager.remove(entity);
                entityManager.flush();
                log.info("Solicitud {} eliminada", requestId);
            }
        }).subscribeOn(Schedulers.boundedElastic()).then();
    }

    @Override
    public Flux<LoanRequest> findPendingRequests() {
        log.debug("Buscando solicitudes pendientes");
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e WHERE e.status = 'PENDING' ORDER BY e.requestDate ASC",
                    LoanRequestEntity.class);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    @Override
    public Flux<LoanRequest> findAllPaginated(int page, int size) {
        log.debug("Buscando todas las solicitudes - pagina: {}, tamano: {}", page, size);
        return Flux.fromIterable(() -> {
            Query query = entityManager.createQuery(
                    "SELECT e FROM LoanRequestEntity e ORDER BY e.requestDate DESC",
                    LoanRequestEntity.class);
            query.setFirstResult(page * size);
            query.setMaxResults(size);
            return query.getResultList().iterator();
        }).map(this::toDomain).subscribeOn(Schedulers.boundedElastic());
    }

    private LoanRequestEntity toEntity(LoanRequest loanRequest) {
        LoanRequestEntity entity = new LoanRequestEntity();
        entity.setRequestId(loanRequest.getRequestId());
        entity.setCustomerId(loanRequest.getCustomerId());
        entity.setAmount(loanRequest.getAmount());
        entity.setTermMonths(loanRequest.getTermMonths());
        entity.setRequestDate(loanRequest.getRequestDate());
        entity.setStatus(loanRequest.getStatus().name());
        entity.setIdempotencyKey(loanRequest.getIdempotencyKey());
        entity.setChannel(determineChannel(loanRequest.getIdempotencyKey()));
        entity.setCreatedAt(java.time.Instant.now());
        entity.setLastUpdated(java.time.Instant.now());
        return entity;
    }

    private LoanRequest toDomain(LoanRequestEntity entity) {
        return new LoanRequest(
                entity.getRequestId(),
                entity.getCustomerId(),
                entity.getAmount(),
                entity.getTermMonths(),
                entity.getRequestDate(),
                LoanStatus.valueOf(entity.getStatus()),
                entity.getIdempotencyKey()
        );
    }

    private String determineChannel(String idempotencyKey) {
        if (idempotencyKey == null) return "UNKNOWN";
        if (idempotencyKey.startsWith("MOBILE-")) return "MOBILE";
        if (idempotencyKey.startsWith("EXT-")) return "EXTERNAL_API";
        return "WEB";
    }
}