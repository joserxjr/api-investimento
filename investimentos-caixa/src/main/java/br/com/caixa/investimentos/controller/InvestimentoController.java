package br.com.caixa.investimentos.controller;

import br.com.caixa.investimentos.dto.SimulacaoRequest;
import br.com.caixa.investimentos.dto.SimulacaoResponse;
import br.com.caixa.investimentos.service.InvestimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class InvestimentoController {

    private final InvestimentoService investimentoService;

    public InvestimentoController(InvestimentoService investimentoService) {
        this.investimentoService = investimentoService;
    }

    @PostMapping("/simular-investimento")
    public ResponseEntity<SimulacaoResponse> simularInvestimento(@RequestBody SimulacaoRequest request) {
        try {
            SimulacaoResponse response = investimentoService.simularInvestimento(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
