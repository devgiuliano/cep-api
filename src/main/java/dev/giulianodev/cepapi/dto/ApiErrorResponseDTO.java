package dev.giulianodev.cepapi.dto;
import java.time.OffsetDateTime;

public record ApiErrorResponseDTO(
        OffsetDateTime timestamp,
        int status,
        String error,
        String message,
        String path
) {}