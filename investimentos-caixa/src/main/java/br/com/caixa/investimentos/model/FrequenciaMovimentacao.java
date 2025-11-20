package br.com.caixa.investimentos.model;

public enum FrequenciaMovimentacao {
    ALTA(0),
    BAIXA(30);

    private final int pontos;

    FrequenciaMovimentacao(int pontos) {
        this.pontos = pontos;
    }
    public int getPontos() {
        return pontos;
    }

}
