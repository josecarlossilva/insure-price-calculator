package br.com.insurepricecalculator.service;

import br.com.insurepricecalculator.enums.Categoria;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculoTarifaService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto calcularPrecoTarifado(Produto produto) {
        double precoBase = produto.getPrecoBase();
        Categoria categoria = produto.getCategoria();

        double precoTarifado = precoBase + (precoBase * categoria.getIof()) + (precoBase * categoria.getPis()) + (precoBase * categoria.getCofins());

        produto.setPrecoTarifado(precoTarifado);

        // Salvar o produto após calcular
        return produtoRepository.save(produto);
    }
}