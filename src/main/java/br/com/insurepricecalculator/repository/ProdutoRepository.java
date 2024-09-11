package br.com.insurepricecalculator.repository;

import br.com.insurepricecalculator.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}