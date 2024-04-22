package br.com.produtosferaapi.repository;

import br.com.produtosferaapi.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    Optional<Produto> findByNome(String nome);
    List<Produto> findByCategoriasId(Long categoriaId);
}
