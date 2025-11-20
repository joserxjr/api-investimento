package br.com.caixa.investimentos.dto;

import java.math.BigDecimal;

public record SimulacaoRequest (
        Long clienteId,
        BigDecimal valor,
        Integer prazoMeses,
        String tipoProduto
        ){}
