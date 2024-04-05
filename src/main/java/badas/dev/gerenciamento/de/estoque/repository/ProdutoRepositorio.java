package badas.dev.gerenciamento.de.estoque.repository;

import badas.dev.gerenciamento.de.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {

    List<Produto> findByNome(String nome);

    List<Produto> findByQuantidadeGreaterThan(int quantidade);

}
