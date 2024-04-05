package badas.dev.gerenciamento.de.estoque.controller;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import badas.dev.gerenciamento.de.estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    public ResponseEntity<List<Produto>> getAllProdutos() {
        List<Produto> produtos = produtoService.getAllprodutos();
        return new ResponseEntity<>(produtos, HttpStatus.OK);
    }
}
