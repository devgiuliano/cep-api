package dev.giulianodev.cepapi.domain;

import dev.giulianodev.cepapi.api.dto.CepResponseDTO;

public interface CepService {
    CepResponseDTO buscarCep(String cep);
}
