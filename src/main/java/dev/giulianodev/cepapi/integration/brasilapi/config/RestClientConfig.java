package dev.giulianodev.cepapi.integration.brasilapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient brasilApiRestClient() {
        return RestClient.builder()
                .baseUrl("https://brasilapi.com.br")
                .build();
    }
}