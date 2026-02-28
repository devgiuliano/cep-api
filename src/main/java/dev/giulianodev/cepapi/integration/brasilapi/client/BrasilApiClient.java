package dev.giulianodev.cepapi.integration.brasilapi.client;

import dev.giulianodev.cepapi.integration.brasilapi.dto.BrasilApiCepResponseDTO;

public interface BrasilApiClient {
    BrasilApiCepResponseDTO consultarCep(String cep);
}
