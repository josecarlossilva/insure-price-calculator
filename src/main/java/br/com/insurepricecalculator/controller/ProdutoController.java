package br.com.insurepricecalculator.controller;

import br.com.insurepricecalculator.converter.ProdutoConverter;
import br.com.insurepricecalculator.dto.ProdutoDTO;
import br.com.insurepricecalculator.model.Produto;
import br.com.insurepricecalculator.service.CalculoTarifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}