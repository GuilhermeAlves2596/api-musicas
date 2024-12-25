package com.api_musicas.mapper;

import com.api_musicas.domain.dto.AlbumDTO;
import com.api_musicas.model.AlbumModel;
import org.springframework.stereotype.Component;
@Component
public class AlbumMapper {

    public AlbumModel dtoToModel(AlbumDTO dto){
        return AlbumModel.builder()
                .titulo(dto.getTitulo())
                .anoLancamento(dto.getAnoLancamento())
                .artista(dto.getArtista())
                .build();
    }

    public AlbumModel dtoToModelUpdate(AlbumDTO dto, AlbumModel model){
        return AlbumModel.builder()
                .id(model.getId())
                .titulo(dto.getTitulo())
                .anoLancamento(dto.getAnoLancamento())
                .imagemCapa(model.getImagemCapa())
                .artista(dto.getArtista())
                .build();
    }
}
