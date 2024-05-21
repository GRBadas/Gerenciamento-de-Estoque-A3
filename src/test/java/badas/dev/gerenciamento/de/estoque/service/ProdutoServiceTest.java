package badas.dev.gerenciamento.de.estoque.service;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import badas.dev.gerenciamento.de.estoque.repository.ProdutoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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

    @Test
    public void testAddProdutoComNomeNulo() {
        Produto produto = new Produto(null, 10);

        assertThrows(IllegalArgumentException.class, () -> produtoService.addProduto(produto));
    }

    @Test
    public void testAddProdutoComQuantidadeNegativa() {
        Produto produto = new Produto("Nome do produto", -1);

        assertThrows(IllegalArgumentException.class, () -> produtoService.addProduto(produto));
    }

    @Test
    public void testGetAllProdutos() {



        Produto produto1 = new Produto("Fone de ouvido", 30);
        Produto produto2 = new Produto("Mouse", 18);
        Produto produto3 = new Produto("Teclado", 9);

        List<Produto> listaProdutos = new ArrayList<>();
        listaProdutos.add(produto1);
        listaProdutos.add(produto2);
        listaProdutos.add(produto3);

        when(produtoRepositorio.findAll()).thenReturn(listaProdutos);


        List<Produto> listaRetornada = produtoService.getAllprodutos();

        assertEquals(3, listaRetornada.size());
        assertEquals("Fone de ouvido", listaRetornada.get(0).getNome());
        assertEquals(30, listaRetornada.get(0).getQuantidade());
        assertEquals("Mouse", listaRetornada.get(1).getNome());
        assertEquals(18, listaRetornada.get(1).getQuantidade());
        assertEquals("Teclado", listaRetornada.get(2).getNome());
        assertEquals(9, listaRetornada.get(2).getQuantidade());

    }

    @Test
    public void testAddProdutoNotFound() {
        Produto produtoParaAdicionar = new Produto("Nome do produto", 10);
        when(produtoRepositorio.save(produtoParaAdicionar)).thenReturn(null);

        Produto produtoAdicionado = produtoService.addProduto(produtoParaAdicionar);

        assertNull(produtoAdicionado);
        verify(produtoRepositorio, times(1)).save(produtoParaAdicionar);
    }

}
