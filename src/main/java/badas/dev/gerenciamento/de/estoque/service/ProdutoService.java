package badas.dev.gerenciamento.de.estoque.service;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import badas.dev.gerenciamento.de.estoque.repository.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    public Produto addProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do produto não pode ser nulo ou vazio");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("A quantidade do produto não pode ser negativa");
        }
        return produtoRepositorio.save(produto);
    }

    public List<Produto> getAllprodutos() {
        return produtoRepositorio.findAll();
    }

}
