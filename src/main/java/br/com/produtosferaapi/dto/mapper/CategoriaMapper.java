package br.com.produtosferaapi.dto.mapper;

import br.com.produtosferaapi.dto.CategoriaRequestDTO;
import br.com.produtosferaapi.dto.CategoriaResponseDTO;
import br.com.produtosferaapi.model.Categoria;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {
    private final ModelMapper mapper;

    public CategoriaMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Categoria toCategoria(CategoriaRequestDTO dto){
        return this.mapper.map(dto, Categoria.class);
    }

    public CategoriaResponseDTO toCategoriaResponseDTO(Categoria categoria){
        return this.mapper.map(categoria, CategoriaResponseDTO.class);
    }
}
