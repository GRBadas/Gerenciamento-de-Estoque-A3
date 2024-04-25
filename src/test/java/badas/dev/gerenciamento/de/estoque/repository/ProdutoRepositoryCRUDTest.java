package badas.dev.gerenciamento.de.estoque.repository;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class ProdutoRepositoryCRUDTest {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    void testCreateProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto 1");
        produto.setQuantidade(5);

        produto = produtoRepositorio.save(produto);
        assertNotNull(produto.getId());
    }

    @Test
    void testReadProduto() {
        Produto produtoSalvo = produtoRepositorio.save(new Produto("Produto 1", 5));

        Optional<Produto> produtoSalvoOPT = produtoRepositorio.findById(produtoSalvo.getId());
        assertTrue(produtoSalvoOPT.isPresent());
    }

    @Test
    void testUpdateProduto() {
        Produto produtoSalvo = produtoRepositorio.save(new Produto("Produto 1", 5));

        produtoSalvo.setNome("Produto Atualizado");
        produtoSalvo.setQuantidade(10);

        produtoRepositorio.save(produtoSalvo);

        Optional<Produto> produtoAtualizadoOPT = produtoRepositorio.findById(produtoSalvo.getId());
        assertTrue(produtoAtualizadoOPT.isPresent());

        Produto produtoAtualizado = produtoAtualizadoOPT.get();
        assertEquals("Produto Atualizado", produtoAtualizado.getNome());
        assertEquals(10, produtoAtualizado.getQuantidade());
    }

    @Test
    void testDeleteProduto() {
        Produto produtoSalvo = produtoRepositorio.save(new Produto("Produto 1", 5));

        produtoRepositorio.deleteById(produtoSalvo.getId());

        Optional<Produto> produtoDeletadoOPT = produtoRepositorio.findById(produtoSalvo.getId());
        assertFalse(produtoDeletadoOPT.isPresent());
    }
}
