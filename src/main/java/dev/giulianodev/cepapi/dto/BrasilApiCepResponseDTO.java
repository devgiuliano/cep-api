package dev.giulianodev.cepapi.dto;


public record BrasilApiCepResponseDTO (
     String cep,
     String state,
     String city,
     String neighborhood,
     String street,
     String service

){}
