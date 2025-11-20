package br.com.caixa.investimentos.controller;


import br.com.caixa.investimentos.dto.PerfilRiscoResponse;
import br.com.caixa.investimentos.service.PerfilRiscoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class PerfilRiscoController {


    private final PerfilRiscoService perfilRiscoService;

    public PerfilRiscoController(PerfilRiscoService perfilRiscoService) {
        this.perfilRiscoService = perfilRiscoService;
    }

    @GetMapping("/perfil-risco/{clienteId}")
    public ResponseEntity<PerfilRiscoResponse> obterPerfilRisco(@PathVariable Long clienteId) {
        try {
            PerfilRiscoResponse response = perfilRiscoService.obterPerfilRisco(clienteId);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


}
