package dev.giulianodev.cepapi.integration.brasilapi.dto;
import java.time.OffsetDateTime;

public record ApiErrorResponseDTO(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}