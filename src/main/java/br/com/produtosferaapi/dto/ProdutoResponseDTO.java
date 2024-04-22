package br.com.produtosferaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class ProdutoResponseDTO {
    private Long id;

    @NotBlank(message = "O código do produto é obrigatório")
    @Size(max = 10, message = "O código do produto deve ter no máximo 10 caracteres")
    private String codigo;

    @NotBlank(message = "O nome do produto é obrigatório")
    @Size(max = 100, message = "O nome do produto deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 255, message = "A descrição do produto deve ter no máximo 255 caracteres")
    private String descricao;

    @NotNull(message = "O preço do produto é obrigatório")
    @Positive(message = "O preço do produto deve ser um número positivo")
    private double preco;

    @NotNull(message = "A quantidade em estoque do produto é obrigatória")
    @Positive(message = "A quantidade em estoque do produto deve ser um número positivo")
    private int quantidadeEstoque;
    
    private Set<CategoriaResponseDTO> categorias;
}
