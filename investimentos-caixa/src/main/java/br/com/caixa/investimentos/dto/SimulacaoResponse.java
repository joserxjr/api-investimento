package br.com.caixa.investimentos.dto;

import java.time.LocalDateTime;

public record SimulacaoResponse (
        ProdutoValidado produtoValidado,
        ResultadoSimulacao resultadoSimulacao,
        LocalDateTime dataSimulacao
        ){}
