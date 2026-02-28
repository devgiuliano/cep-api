package dev.giulianodev.cepapi.service;

import dev.giulianodev.cepapi.client.BrasilApiClient;
import dev.giulianodev.cepapi.dto.BrasilApiCepResponseDTO;
import dev.giulianodev.cepapi.dto.CepResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class CepServiceImpl implements CepService {
    private final BrasilApiClient brasilApiClient;

    public CepServiceImpl(BrasilApiClient brasilApiClient) {
        this.brasilApiClient = brasilApiClient;
    }

    @Override
    public CepResponseDTO buscarCep(String cep) {
        BrasilApiCepResponseDTO externo = brasilApiClient.consultarCep(cep);
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
