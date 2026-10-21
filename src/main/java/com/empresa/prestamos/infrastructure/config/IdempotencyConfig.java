package com.empresa.prestamos.infrastructure.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class IdempotencyConfig {

    private static final Logger log = LoggerFactory.getLogger(IdempotencyConfig.class);
    private static final String IDEMPOTENCY_CACHE_NAME = "idempotency-cache";
    private static final String PROCESSING_CACHE_NAME = "idempotency-processing";

    @Value("${idempotency.cache.ttl-minutes:60}")
    private int cacheTtlMinutes;

    @Value("${idempotency.max-entries:10000}")
    private int maxCacheEntries;

    @Value("${idempotency.cleanup-interval-minutes:10}")
    private int cleanupIntervalMinutes;

    @Bean
    @Primary
    public CacheManager idempotencyCacheManager() {
        log.info("Configuring idempotency cache manager with TTL: {} minutes, max entries: {}",
                cacheTtlMinutes, maxCacheEntries);

        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager(
                IDEMPOTENCY_CACHE_NAME,
                PROCESSING_CACHE_NAME
        );

        cacheManager.setStoreByValue(false);
        return cacheManager;
    }

    @Bean
    public IdempotencyCacheCleaner idempotencyCacheCleaner(CacheManager cacheManager) {
        return new IdempotencyCacheCleaner(cacheManager, cleanupIntervalMinutes);
    }

    @Bean
    public IdempotencyKeyValidator idempotencyKeyValidator() {
        return new IdempotencyKeyValidator();
    }

    public static class IdempotencyCacheCleaner implements Runnable {
        private final CacheManager cacheManager;
        private final int intervalMinutes;
        private volatile boolean running = true;

        public IdempotencyCacheCleaner(CacheManager cacheManager, int intervalMinutes) {
            this.cacheManager = cacheManager;
            this.intervalMinutes = intervalMinutes;
        }

        @Override
        public void run() {
            log.debug("Idempotency cache cleanup task started");
            while (running) {
                try {
                    Thread.sleep(TimeUnit.MINUTES.toMillis(intervalMinutes));
                    performCleanup();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.warn("Idempotency cache cleaner interrupted");
                    break;
                }
            }
        }

        private void performCleanup() {
            log.debug("Performing idempotency cache cleanup");
        }

        public void stop() {
            this.running = false;
        }
    }

    public static class IdempotencyKeyValidator {
        private static final int MIN_KEY_LENGTH = 8;
        private static final int MAX_KEY_LENGTH = 128;
        private static final java.util.regex.Pattern KEY_PATTERN = 
                java.util.regex.Pattern.compile("^[a-zA-Z0-9_-]+$");

        public void validate(String key, String channel) {
            if (key == null || key.isBlank()) {
                throw new IllegalArgumentException("Idempotency key cannot be null or blank");
            }

            if (key.length() < MIN_KEY_LENGTH) {
                throw new IllegalArgumentException(
                        String.format("Idempotency key must be at least %d characters", MIN_KEY_LENGTH));
            }

            if (key.length() > MAX_KEY_LENGTH) {
                throw new IllegalArgumentException(
                        String.format("Idempotency key cannot exceed %d characters", MAX_KEY_LENGTH));
            }

            if (!KEY_PATTERN.matcher(key).matches()) {
                throw new IllegalArgumentException(
                        "Idempotency key must contain only alphanumeric characters, hyphens and underscores");
            }

            if (channel == null || channel.isBlank()) {
                throw new IllegalArgumentException("Idempotency channel cannot be null or blank");
            }
        }

        public String buildCompositeKey(String key, String channel) {
            return channel + ":" + key;
        }
    }
}