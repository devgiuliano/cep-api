package dev.giulianodev.cepapi.domain;

import dev.giulianodev.cepapi.exception.CepInvalidoException;
import dev.giulianodev.cepapi.integration.brasilapi.client.BrasilApiClient;
import dev.giulianodev.cepapi.api.dto.CepResponseDTO;
import dev.giulianodev.cepapi.mapper.Brasilapimapper;
import org.springframework.stereotype.Service;

@Service
public class CepServiceImpl implements CepService {
    private final BrasilApiClient brasilApiClient;

    public CepServiceImpl(BrasilApiClient brasilApiClient) {
        this.brasilApiClient = brasilApiClient;
    }

    @Override
    public CepResponseDTO buscarCep(String cep) {
        String cepNormalizado = normalizarCep(cep);
        validarCep(cepNormalizado);

        var externo = brasilApiClient.consultarCep(cepNormalizado);
        return Brasilapimapper.toInternal(externo);
    }

    private String normalizarCep(String cep) {
        if (cep == null)
            return "";
        return cep.replace("-", "").trim();
    }

    private void validarCep(String cep) {
        if (!cep.matches("\\d{8}")) {
            throw new CepInvalidoException(
                    "CEP inválido. Use 8 dígitos (ex: 01001000) ou com hífen (ex: 01001-000)."
            );
        }
    }

}
