package br.com.caixa.investimentos;

import br.com.caixa.investimentos.model.*;
import br.com.caixa.investimentos.repository.ClienteRepository;
import br.com.caixa.investimentos.repository.ProdutoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public DataInitializer(ClienteRepository clienteRepository, ProdutoRepository produtoRepository) {
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public void run(String... args) {
        if (clienteRepository.count() == 0) {
            Cliente cliente1 = new Cliente(
                    VolumeInvestimento.BAIXO,
                    FrequenciaMovimentacao.ALTA,
                    PreferenciaInvestimento.LIQUIDEZ
            );
            clienteRepository.save(cliente1);


            Cliente cliente2 = new Cliente(
                    VolumeInvestimento.ALTO,
                    FrequenciaMovimentacao.BAIXA,
                    PreferenciaInvestimento.LIQUIDEZ
            );
            clienteRepository.save(cliente2);


            Cliente cliente3 = new Cliente(
                    VolumeInvestimento.ALTO,
                    FrequenciaMovimentacao.BAIXA,
                    PreferenciaInvestimento.RENTABILIDADE
            );
            clienteRepository.save(cliente3);

            if (produtoRepository.count() == 0) {
                Produto cdb = new Produto("CDB Caixa 2026", "CDB", new BigDecimal("0.12"), "Baixo");
                produtoRepository.save(cdb);

                Produto fundo = new Produto("Fundo Multimercado Caixa", "FUNDO_MULTIMERCADO",
                        new BigDecimal("0.15"), "Médio");
                produtoRepository.save(fundo);

                Produto acao = new Produto("Ações Caixa", "ACAO", new BigDecimal("0.20"), "Alto");
                produtoRepository.save(acao);
            }
        }
    }
}
