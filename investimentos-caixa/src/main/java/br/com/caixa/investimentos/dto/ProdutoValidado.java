package br.com.caixa.investimentos.dto;

import java.math.BigDecimal;

public record ProdutoValidado(
        Long id,
        String nome,
        String tipo,
        BigDecimal rentabilidade,
        String risco
        ) {}
