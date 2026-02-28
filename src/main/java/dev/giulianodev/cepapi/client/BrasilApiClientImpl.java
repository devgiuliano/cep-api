package dev.giulianodev.cepapi.client;

import dev.giulianodev.cepapi.dto.BrasilApiCepResponseDTO;
import dev.giulianodev.cepapi.exception.CepNaoEncontradoException;
import dev.giulianodev.cepapi.exception.ServicoExternoException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClient;
@Component
public class BrasilApiClientImpl implements BrasilApiClient {
    private final RestClient restClient;

    public BrasilApiClientImpl(RestClient restClient) {
        this.restClient = restClient;
    }

    @Override
    public BrasilApiCepResponseDTO consultarCep(String cep) {
        try {
            return restClient.get()
                    .uri("/api/cep/v1/{cep}", cep)
                    .retrieve()
                    .body(BrasilApiCepResponseDTO.class);
        } catch (HttpClientErrorException.NotFound ex) {
            throw new CepNaoEncontradoException("CEP não encontrado em nenhum provider");
        } catch (HttpServerErrorException ex) {
            throw new ServicoExternoException("BrasilAPI retornou erro 5xx", ex);
        } catch (Exception ex) {
            throw new ServicoExternoException("Erro ao chamar BrasilAPI", ex);
        }
    }
}
