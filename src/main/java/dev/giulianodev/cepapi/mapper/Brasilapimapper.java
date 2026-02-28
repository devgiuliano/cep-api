package dev.giulianodev.cepapi.mapper;

import dev.giulianodev.cepapi.api.dto.CepResponseDTO;
import dev.giulianodev.cepapi.integration.brasilapi.dto.BrasilApiCepResponseDTO;

public class Brasilapimapper {
    private Brasilapimapper() {}

    public static CepResponseDTO toInternal(BrasilApiCepResponseDTO externo) {
        return new CepResponseDTO(
                externo.cep(),
                externo.state(),
                externo.city(),
                externo.neighborhood(),
                externo.street(),
                externo.service()
        );
    }
}
