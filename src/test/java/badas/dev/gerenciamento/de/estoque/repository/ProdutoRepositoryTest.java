package badas.dev.gerenciamento.de.estoque.repository;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}
