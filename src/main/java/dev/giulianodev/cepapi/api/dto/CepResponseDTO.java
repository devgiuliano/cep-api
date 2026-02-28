package dev.giulianodev.cepapi.api.dto;

public record CepResponseDTO (
     String cep,
     String estado,
     String cidade,
     String bairro,
     String rua,
     String servico

) {}
