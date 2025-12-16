package br.com.caixa.investimentos.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record SimulacaoRequest (

        @NotNull(message = "Id não pode ser nulo")
        @Positive(message = "O Id deve ser positivo")
        Long clienteId,
        @NotNull(message = "O valor do investimento é obrigatório")
        @DecimalMin(value = "100.00", message = "O valor mínimo é de R$ 100,00 ")
        @DecimalMax(value = "10000000.00", message = "O valor máximo é de 10.000.000,00")
        BigDecimal valor,
        @NotNull(message = "O prazo deve ser preenchido")
        @Min(value = 1, message = "Prazo deve ser maior que 1")
        @Max(value = 360, message = "Prazo maximo de 360 meses")
        Integer prazoMeses,
        @NotNull(message = "Tipo do produto deve ser preenchido")
        String tipoProduto
        ){}
