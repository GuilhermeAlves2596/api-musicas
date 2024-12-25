package com.api_musicas.mapper;

import com.api_musicas.domain.dto.ArtistaDTO;
import com.api_musicas.model.ArtistaModel;
import org.springframework.stereotype.Component;

@Component
public class ArtistaMapper {

    public ArtistaModel dtoToModel(ArtistaDTO dto){
        return ArtistaModel.builder()
                .nome(dto.getNome())
                .nacionalidade(dto.getNacionalidade())
                .site(dto.getSite())
                .build();
    }

    public ArtistaModel dtoToModelUpdate(ArtistaDTO dto, ArtistaModel model){
        return ArtistaModel.builder()
                .id(model.getId())
                .nome(dto.getNome())
                .nacionalidade(dto.getNacionalidade())
                .site(dto.getSite())
                .imagemPerfil(model.getImagemPerfil())
                .build();
    }
}
