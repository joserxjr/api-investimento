package br.com.caixa.investimentos.dto;

public record PerfilRiscoResponse(
        Long clienteId,
        String perfil,
        Integer pontuacao,
        String descricao
        ) {}

