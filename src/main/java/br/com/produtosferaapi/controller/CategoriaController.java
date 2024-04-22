package br.com.produtosferaapi.controller;

import br.com.produtosferaapi.dto.CategoriaRequestDTO;
import br.com.produtosferaapi.dto.CategoriaResponseDTO;
import br.com.produtosferaapi.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/categorias/")
@AllArgsConstructor
@Tag(name = "Categorias", description = "API para operações relacionadas a categorias")
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria com base nos dados fornecidos.")
    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody @Valid CategoriaRequestDTO categoria) {
        try {
            CategoriaResponseDTO novaCategoria = categoriaService.criarCategoria(categoria);
            return ResponseEntity.status(HttpStatus.CREATED).body(novaCategoria);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Erro ao criar categoria: " + ex.getMessage());
        }
    }

    @Operation(summary = "Listar todas as categorias", description = "Recupera uma lista de todas as categorias disponíveis.")
    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        List<CategoriaResponseDTO> categorias = categoriaService.listarCategorias();
        return ResponseEntity.ok(categorias);
    }

    @Operation(summary = "Buscar uma categoria por ID", description = "Recupera uma categoria específica com base no ID fornecido.")
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarCategoriaPorId(@PathVariable Long id) {
        try {
            CategoriaResponseDTO categoria = categoriaService.buscarCategoriaPorId(id);
            return ResponseEntity.ok(categoria);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Operation(summary = "Atualizar uma categoria por ID", description = "Atualiza uma categoria existente com base no ID fornecido.")
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable Long id, @RequestBody @Valid CategoriaRequestDTO categoria) {
        try {
            CategoriaResponseDTO categoriaAtualizada = categoriaService.atualizarCategoria(id, categoria);
            return ResponseEntity.ok(categoriaAtualizada);
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @Operation(summary = "Excluir uma categoria por ID", description = "Exclui uma categoria existente com base no ID fornecido.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCategoria(@PathVariable Long id) {
        try {
            categoriaService.excluirCategoria(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        } catch (SQLException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível excluir a categoria devido a produtos associados a ela.", e);
        }
    }
}