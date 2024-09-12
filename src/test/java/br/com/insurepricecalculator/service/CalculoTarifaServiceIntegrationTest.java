package br.com.insurepricecalculator.service;

import static org.junit.jupiter.api.Assertions.*;

import br.com.insurepricecalculator.enums.Categoria;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("test")
public class CalculoTarifaServiceIntegrationTest {

    @Autowired
    private CalculoTarifaService calculoTarifaService;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Test
    public void testCalculoPrecoTarifadoSeguroVida() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria(Categoria.VIDA);
        produto.setPrecoBase(new BigDecimal("100.0"));

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(new BigDecimal("103.20"), resultado.getPrecoTarifado());

        Optional<Produto> produtoSalvo = produtoRepository.findById(resultado.getId());
        assertTrue(produtoSalvo.isPresent());
        assertEquals(new BigDecimal("103.20"), produtoSalvo.get().getPrecoTarifado());
        assertEquals("Seguro de Vida Individual", produtoSalvo.get().getNome());
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroAuto() {
        Produto produto = new Produto();
        produto.setNome("Seguro Auto");
        produto.setCategoria(Categoria.AUTO);
        produto.setPrecoBase(new BigDecimal("50.0"));

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(new BigDecimal("55.25"), resultado.getPrecoTarifado());

        Optional<Produto> produtoSalvo = produtoRepository.findById(resultado.getId());
        assertTrue(produtoSalvo.isPresent());
        assertEquals(new BigDecimal("55.25"), produtoSalvo.get().getPrecoTarifado());
        assertEquals("Seguro Auto", produtoSalvo.get().getNome());
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroViagem() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Viagem");
        produto.setCategoria(Categoria.VIAGEM);
        produto.setPrecoBase(new BigDecimal("200.0"));

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(new BigDecimal("214.00"), resultado.getPrecoTarifado());

        Optional<Produto> produtoSalvo = produtoRepository.findById(resultado.getId());
        assertTrue(produtoSalvo.isPresent());
        assertEquals(new BigDecimal("214.00"), produtoSalvo.get().getPrecoTarifado());
        assertEquals("Seguro de Viagem", produtoSalvo.get().getNome());
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroResidencial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Residencial");
        produto.setCategoria(Categoria.RESIDENCIAL);
        produto.setPrecoBase(new BigDecimal("300.0"));

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(new BigDecimal("321.00"), resultado.getPrecoTarifado());

        Optional<Produto> produtoSalvo = produtoRepository.findById(resultado.getId());
        assertTrue(produtoSalvo.isPresent());
        assertEquals(new BigDecimal("321.00"), produtoSalvo.get().getPrecoTarifado());
        assertEquals("Seguro Residencial", produtoSalvo.get().getNome());
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroPatrimonial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Patrimonial");
        produto.setCategoria(Categoria.PATRIMONIAL);
        produto.setPrecoBase(new BigDecimal("400.0"));

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(new BigDecimal("432.00"), resultado.getPrecoTarifado());

        Optional<Produto> produtoSalvo = produtoRepository.findById(resultado.getId());
        assertTrue(produtoSalvo.isPresent());
        assertEquals(new BigDecimal("432.00"), produtoSalvo.get().getPrecoTarifado());
        assertEquals("Seguro Patrimonial", produtoSalvo.get().getNome());
    }
}