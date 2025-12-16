package br.com.caixa.investimentos.controller;

import br.com.caixa.investimentos.dto.SimulacaoHistoricoResponse;
import br.com.caixa.investimentos.service.SimulacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SimulacaoController {

    private final SimulacaoService simulacaoService;

    public SimulacaoController(SimulacaoService simulacaoService) {
        this.simulacaoService = simulacaoService;
    }

    @GetMapping("/simulacoes")
    public ResponseEntity<List<SimulacaoHistoricoResponse>> buscarSimulacoes(@RequestParam Long clienteId) {
            List<SimulacaoHistoricoResponse> historico = simulacaoService.buscarHistoricoSimulacoes(clienteId);
            return ResponseEntity.ok(historico);
    }

}
