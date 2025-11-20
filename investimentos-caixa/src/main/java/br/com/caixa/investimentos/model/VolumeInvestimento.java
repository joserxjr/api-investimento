package br.com.caixa.investimentos.model;

public enum VolumeInvestimento {
    ALTO(30),
    BAIXO(0);

    private final int pontos;

    VolumeInvestimento(int pontos) {
        this.pontos = pontos;
    }

    public int getPontos() {
        return pontos;
    }

}
