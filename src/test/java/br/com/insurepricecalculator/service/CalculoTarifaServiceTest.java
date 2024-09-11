package br.com.insurepricecalculator.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.insurepricecalculator.enums.Categoria;
import br.com.insurepricecalculator.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CalculoTarifaServiceTest {

    @Autowired
    private CalculoTarifaService calculoTarifaService;

    @Test
    public void testCalculoPrecoTarifadoSeguroVida() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria(Categoria.VIDA);
        produto.setPrecoBase(100.0);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(103.20, resultado.getPrecoTarifado(), 0.01);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroAuto() {
        Produto produto = new Produto();
        produto.setNome("Seguro Auto");
        produto.setCategoria(Categoria.AUTO);
        produto.setPrecoBase(50.0);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(55.25, resultado.getPrecoTarifado(), 0.01);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroViagem() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Viagem");
        produto.setCategoria(Categoria.VIAGEM);
        produto.setPrecoBase(200.0);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(214.00, resultado.getPrecoTarifado(), 0.01);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroResidencial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Residencial");
        produto.setCategoria(Categoria.RESIDENCIAL);
        produto.setPrecoBase(300.0);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(321.00, resultado.getPrecoTarifado(), 0.01);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroPatrimonial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Patrimonial");
        produto.setCategoria(Categoria.PATRIMONIAL);
        produto.setPrecoBase(400.0);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(432.00, resultado.getPrecoTarifado(), 0.01);
    }
}