package br.com.produtosferaapi.dto.mapper;

import br.com.produtosferaapi.dto.ProdutoRequestDTO;
import br.com.produtosferaapi.dto.ProdutoResponseDTO;
import br.com.produtosferaapi.model.Produto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {
    private final ModelMapper mapper;

    public ProdutoMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Produto toProduto(ProdutoRequestDTO dto){
        return this.mapper.map(dto, Produto.class);
    }

    public ProdutoResponseDTO toProdutoResponseDTO(Produto produto){
        return this.mapper.map(produto, ProdutoResponseDTO.class);
    }
}
