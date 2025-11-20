package br.com.caixa.investimentos.dto;

import java.math.BigDecimal;

public record ResultadoSimulacao (
        BigDecimal valorFinal,
        BigDecimal rentabilidadeEfetiva,
        Integer prazoMeses
        ){}
