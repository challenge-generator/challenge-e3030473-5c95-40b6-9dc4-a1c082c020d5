package com.empresa.prestamos.domain.exception;

import java.time.Instant;
import java.util.UUID;

public final class CreditCheckServiceException extends RuntimeException {

    private final String serviceName;
    private final String customerId;
    private final String errorCode;
    private final Instant timestamp;
    private final boolean retryable;
    private final UUID correlationId;

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode) {
        this(serviceName, customerId, errorCode, null, true, UUID.randomUUID());
    }

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode, Throwable cause) {
        this(serviceName, customerId, errorCode, cause, true, UUID.randomUUID());
    }

    public CreditCheckServiceException(String serviceName, String customerId, String errorCode, 
                                        Throwable cause, boolean retryable, UUID correlationId) {
        super(buildMessage(serviceName, customerId, errorCode), cause);
        this.serviceName = serviceName;
        this.customerId = customerId;
        this.errorCode = errorCode;
        this.timestamp = Instant.now();
        this.retryable = retryable;
        this.correlationId = correlationId;
    }

    private static String buildMessage(String serviceName, String customerId, String errorCode) {
        return String.format("Credit check failed for customer %s. Service: %s, Error: %s", 
                             customerId, serviceName, errorCode);
    }

    public String getServiceName() {
        return serviceName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public boolean isRetryable() {
        return retryable;
    }

    public UUID getCorrelationId() {
        return correlationId;
    }

    public static CreditCheckServiceException serviceUnavailable(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "SERVICE_UNAVAILABLE", 
                                                new RuntimeException("External service timeout"), 
                                                true, UUID.randomUUID());
    }

    public static CreditCheckServiceException invalidResponse(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "INVALID_RESPONSE", 
                                                new RuntimeException("Malformed response from credit service"), 
                                                false, UUID.randomUUID());
    }

    public static CreditCheckServiceException authenticationFailure(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "AUTH_FAILURE", 
                                                new RuntimeException("Authentication failed with credit service"), 
                                                false, UUID.randomUUID());
    }

    public static CreditCheckServiceException rateLimitExceeded(String serviceName, String customerId) {
        return new CreditCheckServiceException(serviceName, customerId, "RATE_LIMIT_EXCEEDED", 
                                                new RuntimeException("Too many requests to credit service"), 
                                                true, UUID.randomUUID());
    }
}