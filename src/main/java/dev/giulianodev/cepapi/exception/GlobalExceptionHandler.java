package dev.giulianodev.cepapi.exception;

import dev.giulianodev.cepapi.dto.ApiErrorResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.OffsetDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiErrorResponseDTO> build(HttpStatus status, String message, HttpServletRequest req) {
        ApiErrorResponseDTO body = new ApiErrorResponseDTO(
                OffsetDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                req.getRequestURI()
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(CepInvalidoException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleCepInvalido(CepInvalidoException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_REQUEST, ex.getMessage(), req);
    }

    @ExceptionHandler(CepNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleCepNaoEncontrado(CepNaoEncontradoException ex, HttpServletRequest req) {
        return build(HttpStatus.NOT_FOUND, ex.getMessage(), req);
    }

    @ExceptionHandler(ServicoExternoException.class)
    public ResponseEntity<ApiErrorResponseDTO> handleServicoExterno(ServicoExternoException ex, HttpServletRequest req) {
        return build(HttpStatus.BAD_GATEWAY, ex.getMessage(), req);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponseDTO> handleGeneric(Exception ex, HttpServletRequest req) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", req);
    }

}
