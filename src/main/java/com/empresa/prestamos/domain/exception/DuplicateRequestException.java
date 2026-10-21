package com.empresa.prestamos.domain.exception;

/**
 * Excepción lanzada cuando se detecta una solicitud duplicada
 * utilizando la clave de idempotencia.
 */
public final class DuplicateRequestException extends RuntimeException {
    private final String idempotencyKey;
    private final String channel;

    public DuplicateRequestException(String idempotencyKey, String channel) {
        super(String.format("Solicitud duplicada detectada con clave '%s' del canal '%s'", 
                idempotencyKey, channel));
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
    }

    public DuplicateRequestException(String idempotencyKey, String channel, Throwable cause) {
        super(String.format("Solicitud duplicada detectada con clave '%s' del canal '%s'", 
                idempotencyKey, channel), cause);
        this.idempotencyKey = idempotencyKey;
        this.channel = channel;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getChannel() {
        return channel;
    }
}