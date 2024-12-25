package com.api_musicas.domain.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MusicaDTO {
    private String tituloMusica;

    private Integer minutos;

    private Integer segundos;

    private Integer numeroFaixa;

    private AlbumDTO album;

    public MusicaDTO(String tituloMusica, Integer minutos, Integer segundos, Integer numeroFaixa) {
        this.tituloMusica = tituloMusica;
        this.minutos = minutos;
        this.segundos = segundos;
        this.numeroFaixa = numeroFaixa;
    }
}
