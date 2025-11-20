package br.com.caixa.investimentos.model;

import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private VolumeInvestimento volumeInvestimento;
    @Enumerated(EnumType.STRING)
    private FrequenciaMovimentacao frequenciaMovimentacao;
    @Enumerated(EnumType.STRING)
    private PreferenciaInvestimento preferenciaInvestimento;
    @Enumerated(EnumType.STRING)
    private PerfilRisco perfilRisco;


    public Cliente(VolumeInvestimento volumeInvestimento,
                   FrequenciaMovimentacao frequenciaMovimentacao,
                   PreferenciaInvestimento preferenciaInvestimento) {
        this.volumeInvestimento = volumeInvestimento;
        this.frequenciaMovimentacao = frequenciaMovimentacao;
        this.preferenciaInvestimento = preferenciaInvestimento;
        this.perfilRisco = calcularPerfilRisco();
    }

    public Cliente() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VolumeInvestimento getVolumeInvestimento() {
        return volumeInvestimento;
    }

    public void setVolumeInvestimento(VolumeInvestimento volumeInvestimento) {
        this.volumeInvestimento = volumeInvestimento;
        recalcularPerfil();
    }

    public FrequenciaMovimentacao getFrequenciaMovimentacao() {
        return frequenciaMovimentacao;
    }

    public void setFrequenciaMovimentacao(FrequenciaMovimentacao frequenciaMovimentacao) {
        this.frequenciaMovimentacao = frequenciaMovimentacao;
        recalcularPerfil();
    }

    public PreferenciaInvestimento getPreferenciaInvestimento() {
        return preferenciaInvestimento;
    }

    public void setPreferenciaInvestimento(PreferenciaInvestimento preferenciaInvestimento) {
        this.preferenciaInvestimento = preferenciaInvestimento;
        recalcularPerfil();
    }

    public PerfilRisco getPerfilRisco() {
        return perfilRisco;
    }



    public int calcularPontuacao() {
        if (volumeInvestimento == null || frequenciaMovimentacao == null || preferenciaInvestimento == null) {
            return 0;
        }
        return volumeInvestimento.getPontos() +
                frequenciaMovimentacao.getPontos() +
                preferenciaInvestimento.getPontos();
    }

    private PerfilRisco calcularPerfilRisco() {
        int pontuacao = calcularPontuacao();

        if (pontuacao <= 40) {
            return PerfilRisco.CONSERVADOR;
        } else if (pontuacao <= 70) {
            return PerfilRisco.MODERADO;
        } else {
            return PerfilRisco.ARROJADO;
        }
    }

    private void recalcularPerfil() {
        this.perfilRisco = calcularPerfilRisco();
    }

}
