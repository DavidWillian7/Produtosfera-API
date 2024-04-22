package br.com.produtosferaapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CategoriaResponseDTO {
    private Long id;

    @NotBlank(message = "O nome da categoria é obrigatório")
    @Size(max = 100, message = "O nome da categoria deve ter no máximo 100 caracteres")
    private String nome;

    @Size(max = 255, message = "A descrição da categoria deve ter no máximo 255 caracteres")
    private String descricao;
}
