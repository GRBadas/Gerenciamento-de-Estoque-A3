package badas.dev.gerenciamento.de.estoque.repository;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureTestDatabase
public class ProdutoRepositoryTest {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    void testeSalvarProduto(){
        Produto produto = new Produto();
        produto.setNome("Produto de teste");
        produto.setQuantidade(15);
        Produto produtoSalvo = produtoRepositorio.save(produto);

        assertNotNull(produtoSalvo.getId());
    }

    @Test
    void testeEncontrarProdutoPorNome(){
        Produto produto = new Produto();
        produto.setNome("Produto de teste 2");
        produto.setQuantidade(10);
        produtoRepositorio.save(produto);

        List<Produto> produtosEncontrados = produtoRepositorio.findByNome("Produto de teste 2");

        assertFalse(produtosEncontrados.isEmpty());
        assertEquals(1,produtosEncontrados.size());
        assertEquals("Produto de teste 2", produtosEncontrados.get(0).getNome());

    }

    @Test
    void testeAtualizarProduto() {
        Produto produto = new Produto();
        produto.setNome("Produto Teste");
        produto.setQuantidade(10);
        Produto salvo = produtoRepositorio.save(produto);

        salvo.setNome("Produto Atualizado");
        salvo.setQuantidade(20);
        Produto atualizado = produtoRepositorio.save(salvo);

        assertEquals("Produto Atualizado", atualizado.getNome());
        assertEquals(20, atualizado.getQuantidade());
    }

    @Test
    void testBuscarProdutoPorNomeInexistente() {
        List<Produto> produtos = produtoRepositorio.findByNome("Nome Inexistente");
        assertTrue(produtos.isEmpty());
    }


}
