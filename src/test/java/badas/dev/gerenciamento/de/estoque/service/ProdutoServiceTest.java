package badas.dev.gerenciamento.de.estoque.service;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import badas.dev.gerenciamento.de.estoque.repository.ProdutoRepositorio;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ProdutoServiceTest {

    @Mock
    private ProdutoRepositorio produtoRepositorio;

    @InjectMocks
    private ProdutoService produtoService;

    @Test
    public void testaddProduto() {

        Produto produtoParaAdicionar = new Produto("Nome do produto", 10);
        when(produtoRepositorio.save(produtoParaAdicionar)).thenReturn(produtoParaAdicionar);

        Produto produtoAdicionado = produtoService.addProduto(produtoParaAdicionar);

        assertNotNull(produtoAdicionado);
        assertEquals("Nome do produto", produtoAdicionado.getNome());
        assertEquals(10, produtoAdicionado.getQuantidade());
        verify(produtoRepositorio, times(1)).save(produtoParaAdicionar);
    }
}
