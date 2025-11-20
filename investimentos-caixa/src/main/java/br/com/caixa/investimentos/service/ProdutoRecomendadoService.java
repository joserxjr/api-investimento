package br.com.caixa.investimentos.service;


import br.com.caixa.investimentos.dto.ProdutoValidado;
import br.com.caixa.investimentos.model.PerfilRisco;
import br.com.caixa.investimentos.model.Produto;
import br.com.caixa.investimentos.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProdutoRecomendadoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoRecomendadoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoValidado> obterProdutosRecomendados(String perfil){
        PerfilRisco perfilRisco;
        try {
            perfilRisco = PerfilRisco.valueOf(perfil.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Perfil inválido: " + perfil);
        }

        List<String> tiposPermitidos = obterTiposPermitidos(perfilRisco);

        List<Produto> todosProdutos = produtoRepository.findAll();

        return todosProdutos.stream()
                .filter(produto -> tiposPermitidos.contains(produto.getTipo()))
                .map(this::converterParaProdutoValidado)
                .collect(Collectors.toList());


    }

    private List<String> obterTiposPermitidos(PerfilRisco perfil) {
        return switch (perfil) {
            case CONSERVADOR -> Arrays.asList("CDB");
            case MODERADO -> Arrays.asList("CDB", "FUNDO_MULTIMERCADO");
            case ARROJADO -> Arrays.asList("CDB", "FUNDO_MULTIMERCADO", "ACAO");
        };
    }

    private ProdutoValidado converterParaProdutoValidado(Produto produto) {
        return new ProdutoValidado(
                produto.getId(),
                produto.getNome(),
                produto.getTipo(),
                produto.getRentabilidade(),
                produto.getRisco()
        );
    }


}
