package br.com.insurepricecalculator.dto;

import br.com.insurepricecalculator.enums.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProdutoDTO {

    private String id;

    private String nome;

    private Categoria categoria;

    @JsonProperty("preco_base")
    private BigDecimal precoBase;

    @JsonProperty(value = "preco_tarifado", access = JsonProperty.Access.READ_ONLY) //tratamento pra ignorar valor informado na requisição
    @Positive(message = "Preço base não pode ser negativo")
    private BigDecimal precoTarifado;

}