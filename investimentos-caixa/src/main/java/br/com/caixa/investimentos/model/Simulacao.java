package br.com.caixa.investimentos.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "simulacoes")
public class Simulacao {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long clienteId;
    private Long produtoId;
    @Column(precision = 19, scale = 2)
    private BigDecimal valorInicial;
    @Column(precision = 19, scale = 2)
    private BigDecimal valorFinal;
    private Integer prazoMeses;
    @Column(precision = 19, scale = 2)
    private BigDecimal rentabilidadeEfetiva;
    private LocalDateTime dataSimulacao;

    public Simulacao() {
    }

    public Simulacao(Long clienteId, Long produtoId, BigDecimal valorInicial, BigDecimal valorFinal,
                     Integer prazoMeses, BigDecimal rentabilidadeEfetiva, LocalDateTime dataSimulacao) {
        this.clienteId = clienteId;
        this.produtoId = produtoId;
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.prazoMeses = prazoMeses;
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
        this.dataSimulacao = dataSimulacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public void setProdutoId(Long produtoId) {
        this.produtoId = produtoId;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public Integer getPrazoMeses() {
        return prazoMeses;
    }

    public void setPrazoMeses(Integer prazoMeses) {
        this.prazoMeses = prazoMeses;
    }

    public BigDecimal getRentabilidadeEfetiva() {
        return rentabilidadeEfetiva;
    }

    public void setRentabilidadeEfetiva(BigDecimal rentabilidadeEfetiva) {
        this.rentabilidadeEfetiva = rentabilidadeEfetiva;
    }

    public LocalDateTime getDataSimulacao() {
        return dataSimulacao;
    }

    public void setDataSimulacao(LocalDateTime dataSimulacao) {
        this.dataSimulacao = dataSimulacao;
    }
}
