package dev.giulianodev.cepapi.controller;

import dev.giulianodev.cepapi.dto.CepResponseDTO;
import dev.giulianodev.cepapi.service.CepService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ceps")
public class CepController {
    private final CepService cepService;

    public CepController(CepService cepService) {
        this.cepService = cepService;
    }
    @GetMapping("/{cep}")
    public ResponseEntity<CepResponseDTO> buscarCep(@PathVariable String cep) {
        CepResponseDTO response = cepService.buscarCep(cep);
        return ResponseEntity.ok(response);
    }
}
