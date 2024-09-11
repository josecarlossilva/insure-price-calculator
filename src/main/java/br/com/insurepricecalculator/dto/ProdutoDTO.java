package br.com.insurepricecalculator.dto;

import br.com.insurepricecalculator.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private Long id;

    private String nome;

    private Categoria categoria;

    @JsonProperty("preco_base")
    private double precoBase;

    @JsonProperty(value = "preco_tarifado", access = JsonProperty.Access.READ_ONLY)
    private double precoTarifado;

}