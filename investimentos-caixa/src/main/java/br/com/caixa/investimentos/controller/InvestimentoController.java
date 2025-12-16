package br.com.caixa.investimentos.controller;

import br.com.caixa.investimentos.dto.SimulacaoRequest;
import br.com.caixa.investimentos.dto.SimulacaoResponse;
import br.com.caixa.investimentos.service.InvestimentoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
    public ResponseEntity<SimulacaoResponse> simularInvestimento(@Valid @RequestBody SimulacaoRequest request) {
            SimulacaoResponse response = investimentoService.simularInvestimento(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
