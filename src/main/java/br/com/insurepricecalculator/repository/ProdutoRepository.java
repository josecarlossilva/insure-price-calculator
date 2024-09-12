package br.com.insurepricecalculator.repository;

import br.com.insurepricecalculator.enums.Categoria;
import br.com.insurepricecalculator.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;

public interface ProdutoRepository extends JpaRepository<Produto, String> {

    Produto findByPrecoBaseAndCategoria(BigDecimal precoBase, Categoria categoria);
}