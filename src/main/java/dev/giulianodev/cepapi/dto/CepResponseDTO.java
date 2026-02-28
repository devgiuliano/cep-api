package dev.giulianodev.cepapi.dto;

public record CepResponseDTO (
     String cep,
     String estado,
     String cidade,
     String bairro,
     String rua,
     String servico

) {}
