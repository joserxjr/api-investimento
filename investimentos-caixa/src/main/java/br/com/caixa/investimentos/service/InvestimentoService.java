package br.com.caixa.investimentos.service;

import br.com.caixa.investimentos.dto.*;
import br.com.caixa.investimentos.exception.BusinessRuleException;
import br.com.caixa.investimentos.exception.ResourceNotFoundException;
import br.com.caixa.investimentos.model.*;
import br.com.caixa.investimentos.repository.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.ReadOnlyFileSystemException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;



@Service
public class InvestimentoService {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final SimulacaoRepository simulacaoRepository;

    public InvestimentoService(ClienteRepository clienteRepository,
                               ProdutoRepository produtoRepository,
                               SimulacaoRepository simulacaoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
        this.simulacaoRepository = simulacaoRepository;
    }

    public SimulacaoResponse simularInvestimento(SimulacaoRequest request) {
        Cliente cliente = clienteRepository.findById(request.clienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado"));

        validarPerfilProduto(cliente.getPerfilRisco(), request.tipoProduto());

        Produto produto = produtoRepository.findByTipo(request.tipoProduto())
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        BigDecimal valorFinal = calcularValorFinal(request.valor(), produto.getRentabilidade(),
                                               request.prazoMeses());

        LocalDateTime dataSimulacao = LocalDateTime.now();

        Simulacao simulacao = new Simulacao(
                request.clienteId(),
                produto.getId(),
                request.valor(),
                valorFinal,
                request.prazoMeses(),
                produto.getRentabilidade(),
                dataSimulacao
        );
        simulacaoRepository.save(simulacao);

        ProdutoValidado produtoValidado = new ProdutoValidado(
                produto.getId(),
                produto.getNome(),
                produto.getTipo(),
                produto.getRentabilidade(),
                produto.getRisco()
        );

        ResultadoSimulacao resultadoSimulacao = new ResultadoSimulacao(
                valorFinal,
                produto.getRentabilidade(),
                request.prazoMeses()
        );

        return new SimulacaoResponse(produtoValidado, resultadoSimulacao, dataSimulacao);
    }

    private void validarPerfilProduto(PerfilRisco perfil, String tipoProduto) {
        List<String> produtosPermitidos = switch (perfil) {
            case CONSERVADOR -> Arrays.asList("CDB");
            case MODERADO -> Arrays.asList("CDB", "FUNDO_MULTIMERCADO");
            case ARROJADO -> Arrays.asList("CDB", "FUNDO_MULTIMERCADO", "ACAO");
        };

        if (!produtosPermitidos.contains(tipoProduto)) {
            throw new BusinessRuleException("Produto " + tipoProduto + " não permitido para perfil " + perfil);
        }
    }

    private BigDecimal calcularValorFinal(BigDecimal valorInicial, BigDecimal rentabilidade, int prazoMeses) {

        BigDecimal anos = new BigDecimal(prazoMeses).divide(new BigDecimal("12"), 10, RoundingMode.HALF_UP);

        BigDecimal umMaisRentabilidade = BigDecimal.ONE.add(rentabilidade);
        BigDecimal fator = potencia(umMaisRentabilidade, anos);
        BigDecimal resultado = valorInicial.multiply(fator);

        return resultado.setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal potencia(BigDecimal base, BigDecimal expoente) {
        double baseDouble = base.doubleValue();
        double expoenteDouble = expoente.doubleValue();
        double resultado = Math.pow(baseDouble, expoenteDouble);
        return new BigDecimal(Double.toString(resultado));
    }


}

