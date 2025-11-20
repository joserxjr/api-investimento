package br.com.caixa.investimentos.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record SimulacaoHistoricoResponse(
        Long id,
        Long clienteId,
        String produto,
        BigDecimal valorInvestido,
        BigDecimal valorFinal,
        Integer prazoMeses,
        LocalDateTime dataSimulacao
        ) {}
