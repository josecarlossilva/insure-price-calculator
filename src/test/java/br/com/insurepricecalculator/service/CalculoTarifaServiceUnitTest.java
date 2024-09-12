package br.com.insurepricecalculator.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import br.com.insurepricecalculator.enums.Categoria;
import br.com.insurepricecalculator.exceptions.ProdutoException;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CalculoTarifaServiceUnitTest {

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CalculoTarifaService calculoTarifaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroVida() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Vida Individual");
        produto.setCategoria(Categoria.VIDA);
        produto.setPrecoBase(100.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(103.20, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroAuto() {
        Produto produto = new Produto();
        produto.setNome("Seguro Auto");
        produto.setCategoria(Categoria.AUTO);
        produto.setPrecoBase(50.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(55.25, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroViagem() {
        Produto produto = new Produto();
        produto.setNome("Seguro de Viagem");
        produto.setCategoria(Categoria.VIAGEM);
        produto.setPrecoBase(200.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(214.00, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroResidencial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Residencial");
        produto.setCategoria(Categoria.RESIDENCIAL);
        produto.setPrecoBase(300.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(321.00, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testCalculoPrecoTarifadoSeguroPatrimonial() {
        Produto produto = new Produto();
        produto.setNome("Seguro Patrimonial");
        produto.setCategoria(Categoria.PATRIMONIAL);
        produto.setPrecoBase(400.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(432.00, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    public void testCalculoPrecoSemCategoria() {
        Produto produto = new Produto();
        produto.setNome("Produto Sem Categoria");
        produto.setPrecoBase(100.0);

        Exception exception = assertThrows(ProdutoException.class, () -> {
            calculoTarifaService.calcularPrecoTarifado(produto);
        });

        String expectedMessage = "Informe uma Categoria para o Produto";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCalculoPrecoBaseNegativo() {
        Produto produto = new Produto();
        produto.setNome("Produto com Preço Base Negativo");
        produto.setCategoria(Categoria.VIDA);
        produto.setPrecoBase(-100.0);

        Exception exception = assertThrows(ProdutoException.class, () -> {
            calculoTarifaService.calcularPrecoTarifado(produto);
        });

        String expectedMessage = "Preço base não pode ser negativo";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testCalculoPrecoBaseZero() {
        Produto produto = new Produto();
        produto.setNome("Produto com Preço Base Zero");
        produto.setCategoria(Categoria.RESIDENCIAL);
        produto.setPrecoBase(0.0);

        when(produtoRepository.save(any(Produto.class))).thenReturn(produto);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        assertEquals(0.0, resultado.getPrecoTarifado());
        verify(produtoRepository, times(1)).save(produto);
    }

    @Test
    void calcularPrecoTarifado_QuandoSalvarProdutoLancaExcecao_DeveCapturarERegistrarErro() {
        String message = "Erro ao tentar salvar cálculo de tarifa";

        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setCategoria(Categoria.RESIDENCIAL);
        produto.setPrecoBase(100.0);

        doThrow(new ProdutoException(message)).when(produtoRepository).save(produto);

        ProdutoException exception = assertThrows(ProdutoException.class, () -> {
            calculoTarifaService.calcularPrecoTarifado(produto);
        });

        assertEquals(message, exception.getMessage());
    }

}