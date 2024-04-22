package br.com.produtosferaapi.controller;

import br.com.produtosferaapi.dto.ProdutoRequestDTO;
import br.com.produtosferaapi.dto.ProdutoResponseDTO;
import br.com.produtosferaapi.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/produtos/")
@AllArgsConstructor
@Tag(name = "Produtos", description = "API para operações relacionadas a produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto com base nos dados fornecidos.")
    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> criarProduto(@RequestBody @Valid ProdutoRequestDTO produto) {
        try {
            ProdutoResponseDTO novoProduto = produtoService.criarProduto(produto);
            return ResponseEntity.status(HttpStatus.CREATED).body(novoProduto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar produto: " + ex.getMessage());
        }
    }

    @Operation(summary = "Listar todos os produtos", description = "Recupera uma lista de todos os produtos disponíveis.")
    @GetMapping
    public ResponseEntity<List<ProdutoResponseDTO>> listarProdutos() {
        List<ProdutoResponseDTO> produtos = produtoService.listarProdutos();
        return ResponseEntity.ok(produtos);
    }

    @Operation(summary = "Buscar um produto por ID", description = "Recupera um produto específico com base no ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> buscarProdutoPorId(@PathVariable Long id) {
        try {
            ProdutoResponseDTO produto = produtoService.buscarProdutoPorId(id);
            return ResponseEntity.ok(produto);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Operation(summary = "Atualizar um produto por ID", description = "Atualiza um produto existente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoResponseDTO> atualizarProduto(@PathVariable Long id, @RequestBody @Valid ProdutoRequestDTO produto) {
        try {
            ProdutoResponseDTO produtoAtualizado = produtoService.atualizarProduto(id, produto);
            return ResponseEntity.ok(produtoAtualizado);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Operation(summary = "Excluir um produto por ID", description = "Exclui um produto existente com base no ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirProduto(@PathVariable Long id) {
        try {
            produtoService.excluirProduto(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}