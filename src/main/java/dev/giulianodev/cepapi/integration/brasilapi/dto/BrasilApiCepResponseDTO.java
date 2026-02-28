package dev.giulianodev.cepapi.integration.brasilapi.dto;


public record BrasilApiCepResponseDTO (
     String cep,
     String state,
     String city,
     String neighborhood,
     String street,
     String service

){}
