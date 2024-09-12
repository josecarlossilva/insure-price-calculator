package br.com.insurepricecalculator.controller;

import br.com.insurepricecalculator.converter.ProdutoConverter;
import br.com.insurepricecalculator.dto.ProdutoDTO;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.service.CalculoTarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private CalculoTarifaService calculoTarifaService;

    @PostMapping("/calcular")
    public ResponseEntity<ProdutoDTO> calcularPrecoTarifado(@RequestBody ProdutoDTO produtoDTO) {
        Produto produto = ProdutoConverter.convertToEntity(produtoDTO);

        Produto resultado = calculoTarifaService.calcularPrecoTarifado(produto);

        return new ResponseEntity<>(ProdutoConverter.convertToDTO(resultado), HttpStatus.OK);
    }

    /**
     *
     * - @ExceptionHandler(Exception.class): Esta anotação especifica que o método handleError
     *   deve tratar todas as exceções do tipo Exception lançadas por qualquer método no controlador.
     *
     * - @ResponseStatus(HttpStatus.OK): Esta anotação define o código de status HTTP da resposta como
     *   200 OK quando uma exceção é tratada para fins de seguranca.
     *
     */

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Map<String, String>> handleError(Exception ex) {
        Map<String, String> responseBody = new HashMap<>();
        responseBody.put("msg", ex.getMessage());
        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }
}