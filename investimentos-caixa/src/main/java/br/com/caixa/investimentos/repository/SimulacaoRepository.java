package br.com.caixa.investimentos.repository;

import br.com.caixa.investimentos.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
    List<Simulacao> findByClienteId(Long clienteId);
}
