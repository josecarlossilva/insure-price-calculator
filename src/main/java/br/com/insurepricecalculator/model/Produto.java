package br.com.insurepricecalculator.model;

import br.com.insurepricecalculator.enums.Categoria;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.UUID;


@Data
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    private String id;

    private String nome;

    private Categoria categoria;

    private BigDecimal precoBase;

    private BigDecimal precoTarifado;

    @PrePersist
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    public void calcularPrecoTarifado() {
        BigDecimal precoBase = this.getPrecoBase();
        Categoria categoria = this.getCategoria();

        MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

        BigDecimal iof = BigDecimal.valueOf(categoria.getIof());
        BigDecimal pis = BigDecimal.valueOf(categoria.getPis());
        BigDecimal cofins = BigDecimal.valueOf(categoria.getCofins());

        BigDecimal precoTarifado = precoBase.add(precoBase.multiply(iof, mc))
                .add(precoBase.multiply(pis, mc))
                .add(precoBase.multiply(cofins, mc));

        setPrecoTarifado(precoTarifado.setScale(2, RoundingMode.HALF_UP));

    }
    
}