package br.com.caixa.investimentos.service;

import br.com.caixa.investimentos.dto.SimulacaoHistoricoResponse;
import br.com.caixa.investimentos.model.Produto;
import br.com.caixa.investimentos.model.Simulacao;
import br.com.caixa.investimentos.repository.ClienteRepository;
import br.com.caixa.investimentos.repository.ProdutoRepository;
import br.com.caixa.investimentos.repository.SimulacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SimulacaoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final SimulacaoRepository simulacaoRepository;

    public SimulacaoService(ClienteRepository clienteRepository, ProdutoRepository produtoRepository,
                            SimulacaoRepository simulacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.simulacaoRepository = simulacaoRepository;
    }

    public List<SimulacaoHistoricoResponse> buscarHistoricoSimulacoes(Long clienteId) {


        if (!clienteRepository.existsById(clienteId)) {
            throw new RuntimeException("Cliente não encontrado");
        }

        List<Simulacao> simulacoes = simulacaoRepository.findByClienteId(clienteId);

        return simulacoes.stream()
                .map(this::converterParaResponse)
                .collect(Collectors.toList());

    }


    private SimulacaoHistoricoResponse converterParaResponse(Simulacao simulacao) {

        String nomeProduto = produtoRepository.findById(simulacao.getProdutoId())
                .map(Produto::getNome)
                .orElse("Produto não encontrado");

        return new SimulacaoHistoricoResponse(
                simulacao.getId(),
                simulacao.getClienteId(),
                nomeProduto,
                simulacao.getValorInicial(),
                simulacao.getValorFinal(),
                simulacao.getPrazoMeses(),
                simulacao.getDataSimulacao()
        );
    }


}