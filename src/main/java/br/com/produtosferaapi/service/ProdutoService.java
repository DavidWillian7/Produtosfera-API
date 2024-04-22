package br.com.produtosferaapi.service;

import br.com.produtosferaapi.dto.ProdutoRequestDTO;
import br.com.produtosferaapi.dto.ProdutoResponseDTO;
import br.com.produtosferaapi.dto.mapper.CategoriaMapper;
import br.com.produtosferaapi.dto.mapper.ProdutoMapper;
import br.com.produtosferaapi.model.Categoria;
import br.com.produtosferaapi.model.Produto;
import br.com.produtosferaapi.repository.CategoriaRepository;
import br.com.produtosferaapi.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProdutoMapper produtoMapper;
    private final CategoriaMapper categoriaMapper;

    public ProdutoService(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository, ProdutoMapper produtoMapper, CategoriaMapper categoriaMapper) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
        this.produtoMapper = produtoMapper;
        this.categoriaMapper = categoriaMapper;
    }

    public ProdutoResponseDTO criarProduto(ProdutoRequestDTO produtoRequestDTO) {
        Produto produto = produtoMapper.toProduto(produtoRequestDTO);
        Optional<Produto> produtoOptional = produtoRepository.findByNome(produto.getNome());
        if(produtoOptional.isPresent()){
            return produtoMapper.toProdutoResponseDTO(produtoOptional.get());
        }

        Set<Categoria> categoriasPersistidas = persistirCategorias(produto.getCategorias());
        produto.setCategorias(categoriasPersistidas);
        Produto produtoSalvo = produtoRepository.save(produto);
        return produtoMapper.toProdutoResponseDTO(produtoSalvo);
    }

    public List<ProdutoResponseDTO> listarProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        return produtos.stream()
                .map(produtoMapper::toProdutoResponseDTO)
                .toList();
    }

    public ProdutoResponseDTO buscarProdutoPorId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (!produtoOptional.isPresent()) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + id);
        }

        return produtoMapper.toProdutoResponseDTO(produtoOptional.get());
    }

    public ProdutoResponseDTO atualizarProduto(Long id, ProdutoRequestDTO produtoRequestDTO) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + id);
        }

        Produto produto = produtoMapper.toProduto(produtoRequestDTO);
        Set<Categoria> categoriasPersistidas = persistirCategorias(produto.getCategorias());
        produto.setCategorias(categoriasPersistidas);
        produto.setId(id);
        Produto produtoAtualizado = produtoRepository.save(produto);
        return produtoMapper.toProdutoResponseDTO(produtoAtualizado);
    }

    public void excluirProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new IllegalArgumentException("Produto não encontrado com o ID: " + id);
        }

        produtoRepository.deleteById(id);
    }

    private Set<Categoria> persistirCategorias(Set<Categoria> categorias) {
        Set<Categoria> categoriasPersistidas = new HashSet<>();

        for (Categoria categoria : categorias) {
            Optional<Categoria> categoriaOptional = categoriaRepository.findByNome(categoria.getNome());
            if (categoriaOptional.isPresent()) {
                categoriasPersistidas.add(categoriaOptional.get());
            } else {
                categoriasPersistidas.add(categoriaRepository.save(categoria));
            }
        }

        return categoriasPersistidas;
    }
}
