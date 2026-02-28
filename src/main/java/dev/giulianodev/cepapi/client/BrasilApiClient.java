package dev.giulianodev.cepapi.client;

import dev.giulianodev.cepapi.dto.BrasilApiCepResponseDTO;

public interface BrasilApiClient {
    BrasilApiCepResponseDTO consultarCep(String cep);
}
