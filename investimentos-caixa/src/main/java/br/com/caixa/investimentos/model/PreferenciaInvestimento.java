package br.com.caixa.investimentos.model;

public enum PreferenciaInvestimento {
    RENTABILIDADE(40),
    LIQUIDEZ(0);

    private final int pontos;

    PreferenciaInvestimento(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }


}
