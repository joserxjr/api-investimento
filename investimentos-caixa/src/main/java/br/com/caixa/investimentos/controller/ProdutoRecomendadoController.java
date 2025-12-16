package br.com.caixa.investimentos.controller;


import br.com.caixa.investimentos.dto.ProdutoValidado;
import br.com.caixa.investimentos.service.ProdutoRecomendadoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class ProdutoRecomendadoController {

    private final ProdutoRecomendadoService produtoRecomendadoService;

    public ProdutoRecomendadoController(ProdutoRecomendadoService produtoRecomendadoService) {
        this.produtoRecomendadoService = produtoRecomendadoService;
    }

    @GetMapping("/produtos-recomendados/{perfil}")
    public ResponseEntity<List<ProdutoValidado>> obterProdutosRecomendados(@PathVariable String perfil) {
            List<ProdutoValidado> produtos = produtoRecomendadoService.obterProdutosRecomendados(perfil);
            return ResponseEntity.ok(produtos);
    }
}
