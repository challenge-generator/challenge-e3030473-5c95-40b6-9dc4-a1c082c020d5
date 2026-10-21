package com.empresa.prestamos.domain.model;

import java.util.Objects;
import java.util.UUID;

/**
 * Modelo de dominio para manejar claves de idempotencia.
 * Garantiza que las solicitudes duplicadas sean detectadas 
 * y manejadas adecuadamente.
 */
public final class IdempotencyKey {
    private final String key;
    private final String channel;

    public IdempotencyKey(String key, String channel) {
        this.key = Objects.requireNonNull(key, "La clave de idempotencia no puede ser nula");
        this.channel = Objects.requireNonNull(channel, "El canal no puede ser nulo");
        
        if (key.isBlank()) {
            throw new IllegalArgumentException("La clave de idempotencia no puede estar vacía");
        }
        
        if (channel.isBlank()) {
            throw new IllegalArgumentException("El canal no puede estar vacío");
        }
    }

    public String getKey() {
        return key;
    }

    public String getChannel() {
        return channel;
    }

    public String getCompositeKey() {
        return channel + "::" + key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IdempotencyKey that = (IdempotencyKey) o;
        return key.equals(that.key) && channel.equals(that.channel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, channel);
    }

    @Override
    public String toString() {
        return "IdempotencyKey{" +
                "key='" + key + '\'' +
                ", channel='" + channel + '\'' +
                '}';
    }
}