package br.com.insurepricecalculator.converter;

import br.com.insurepricecalculator.dto.ProdutoDTO;
import br.com.insurepricecalculator.model.Produto;

public class ProdutoConverter {

    public static ProdutoDTO convertToDTO(Produto produto) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(produto.getId());
        dto.setNome(produto.getNome());
        dto.setCategoria(produto.getCategoria());
        dto.setPrecoBase(produto.getPrecoBase());
        dto.setPrecoTarifado(produto.getPrecoTarifado());
        return dto;
    }

    public static Produto convertToEntity(ProdutoDTO dto) {
        Produto produto = new Produto();
        produto.setId(dto.getId());
        produto.setNome(dto.getNome());
        produto.setCategoria(dto.getCategoria());
        produto.setPrecoBase(dto.getPrecoBase());
        return produto;
    }
}