package br.com.insurepricecalculator.service;

import br.com.insurepricecalculator.exceptions.ProdutoException;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CalculoTarifaService {

    private static final Logger logger = LoggerFactory.getLogger(CalculoTarifaService.class);

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto calcularPrecoTarifado(Produto produto) {
        if (produto != null && produto.getPrecoBase().compareTo(BigDecimal.ZERO) < 0) {
            throw new ProdutoException("Preço base não pode ser negativo");
        }

        if (produto.getCategoria() == null) {
            throw new ProdutoException("Informe uma Categoria para o Produto");
        }

        //verificar se já esxite um produto com taxa já criado
        Produto produtoExistente = produtoRepository.findByPrecoBaseAndCategoria(
                produto.getPrecoBase(), produto.getCategoria());

        if (produtoExistente != null) {
            return produtoExistente;
        }

        try {
            produto.calcularPrecoTarifado();

            // Salvar o produto após calcular
            return produtoRepository.save(produto);
        } catch (Exception e) {
            String msg = "Erro ao tentar salvar cálculo de tarifa";
            logger.error(msg, e);
            throw new ProdutoException(msg);
        }
    }
}