package br.com.caixa.investimentos.repository;

import br.com.caixa.investimentos.model.Simulacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimulacaoRepository extends JpaRepository<Simulacao, Long> {
}
