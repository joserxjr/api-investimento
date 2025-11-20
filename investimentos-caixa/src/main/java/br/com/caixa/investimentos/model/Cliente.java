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
                   PreferenciaInvestimento preferenciaInvestimento,
                   PerfilRisco perfilRisco) {
        this.volumeInvestimento = volumeInvestimento;
        this.frequenciaMovimentacao = frequenciaMovimentacao;
        this.preferenciaInvestimento = preferenciaInvestimento;
        this.perfilRisco = calcularPerfilRisco();
    }

    public Cliente() {

    }

    public PerfilRisco getPerfilRisco() {
        return perfilRisco;
    }

    public void setPerfilRisco(PerfilRisco perfilRisco) {
        this.perfilRisco = perfilRisco;
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
        recalcularPerfil(); // Recalcula quando muda característica
    }

    public PreferenciaInvestimento getPreferenciaInvestimento() {
        return preferenciaInvestimento;
    }

    public void setPreferenciaInvestimento(PreferenciaInvestimento preferenciaInvestimento) {
        this.preferenciaInvestimento = preferenciaInvestimento;
        recalcularPerfil();
    }

    private PerfilRisco calcularPerfilRisco() {
        if (volumeInvestimento == null || frequenciaMovimentacao == null || preferenciaInvestimento == null) {
            return PerfilRisco.CONSERVADOR;
        }

        int pontuacao = volumeInvestimento.getPontos() +
                frequenciaMovimentacao.getPontos() +
                preferenciaInvestimento.getPontos();

        if (pontuacao <= 40) {
            return PerfilRisco.CONSERVADOR;
        } else if (pontuacao <= 70) {
            return PerfilRisco.MODERADO;
        } else {
            return PerfilRisco.ARROJADO;
        }
    }

    public void recalcularPerfil() {
        this.perfilRisco = calcularPerfilRisco();
    }




}
