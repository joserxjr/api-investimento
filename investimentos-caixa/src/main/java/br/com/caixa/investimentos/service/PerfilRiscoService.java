package br.com.caixa.investimentos.service;

import br.com.caixa.investimentos.dto.PerfilRiscoResponse;
import br.com.caixa.investimentos.model.Cliente;
import br.com.caixa.investimentos.repository.ClienteRepository;
import org.springframework.stereotype.Service;

@Service
public class PerfilRiscoService {

    private final ClienteRepository clienteRepository;


    public PerfilRiscoService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }


    public PerfilRiscoResponse obterPerfilRisco(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        int pontuacao = cliente.calcularPontuacao();
        String descricao = obterDescricaoPerfil(cliente.getPerfilRisco());


        return new PerfilRiscoResponse(
                clienteId,
                cliente.getPerfilRisco().name(),
                pontuacao,
                descricao
        );
    }



    private String obterDescricaoPerfil(br.com.caixa.investimentos.model.PerfilRisco perfil) {
        return switch (perfil) {
            case CONSERVADOR -> "Perfil conservador: prioriza segurança e liquidez. Produtos recomendados: CDB.";
            case MODERADO -> "Perfil moderado: equilibra segurança e rentabilidade. Produtos recomendados: CDB e Fundos Multimercado.";
            case ARROJADO -> "Perfil arrojado: busca maior rentabilidade aceitando mais risco. Produtos recomendados: CDB, Fundos Multimercado e Ações.";
        };
    }

}
