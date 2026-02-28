package dev.giulianodev.cepapi.service;

import dev.giulianodev.cepapi.dto.CepResponseDTO;

public interface CepService {
    CepResponseDTO buscarCep(String cep);
}
