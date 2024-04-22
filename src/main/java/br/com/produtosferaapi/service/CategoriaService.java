package br.com.produtosferaapi.service;

import br.com.produtosferaapi.dto.CategoriaRequestDTO;
import br.com.produtosferaapi.dto.CategoriaResponseDTO;
import br.com.produtosferaapi.dto.mapper.CategoriaMapper;
import br.com.produtosferaapi.model.Categoria;
import br.com.produtosferaapi.model.Produto;
import br.com.produtosferaapi.repository.CategoriaRepository;
import br.com.produtosferaapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    private final CategoriaRepository categoriaRepository;
    private final ProdutoRepository produtoRepository;
    private final CategoriaMapper categoriaMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ProdutoRepository produtoRepository, CategoriaMapper categoriaMapper) {
        this.categoriaRepository = categoriaRepository;
        this.produtoRepository = produtoRepository;
        this.categoriaMapper = categoriaMapper;
    }

    public CategoriaResponseDTO criarCategoria(CategoriaRequestDTO categoriaRequestDTO) {
        Categoria categoria = categoriaMapper.toCategoria(categoriaRequestDTO);
        Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(categoria.getNome());
        if(categoriaOptional.isPresent()){
            return categoriaMapper.toCategoriaResponseDTO(categoriaOptional.get());
        }

        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaResponseDTO(categoriaSalva);
    }

    public List<CategoriaResponseDTO> listarCategorias() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream()
                .map(categoriaMapper::toCategoriaResponseDTO)
                .toList();
    }

    public CategoriaResponseDTO buscarCategoriaPorId(Long id) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        if (!categoriaOptional.isPresent()) {
            throw new IllegalArgumentException("Categoria não encontrada com o ID: " + id);
        }

        return categoriaMapper.toCategoriaResponseDTO(categoriaOptional.get());
    }

    public CategoriaResponseDTO atualizarCategoria(Long id, CategoriaRequestDTO categoriaRequestDTO) {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria não encontrada com o ID: " + id);
        }

        Categoria categoria = categoriaMapper.toCategoria(categoriaRequestDTO);
        categoria.setId(id);
        Categoria categoriaAtualizada = categoriaRepository.save(categoria);
        return categoriaMapper.toCategoriaResponseDTO(categoriaAtualizada);
    }

    public void excluirCategoria(Long id) throws SQLException {
        if (!categoriaRepository.existsById(id)) {
            throw new IllegalArgumentException("Categoria não encontrada com o ID: " + id);
        }

        List<Produto> produtoList = produtoRepository.findByCategoriasId(id);
        if(!produtoList.isEmpty()){
            throw new SQLException("Integridade referencial violada ao excluir categoria.");
        }

        categoriaRepository.deleteById(id);
    }
}
