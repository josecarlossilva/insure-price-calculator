package br.com.insurepricecalculator.model;

import br.com.insurepricecalculator.enums.Categoria;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Categoria categoria;

    private double precoBase;

    private double precoTarifado;

    public void calcularPrecoTarifado() {
        double precoBase = this.getPrecoBase();
        Categoria categoria = this.getCategoria();

        double precoTarifado = precoBase + (precoBase * categoria.getIof()) + (precoBase * categoria.getPis()) + (precoBase * categoria.getCofins());

        setPrecoTarifado(precoTarifado);
    }
    
}