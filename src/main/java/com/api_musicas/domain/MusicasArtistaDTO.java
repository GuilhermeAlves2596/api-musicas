package com.api_musicas.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MusicasArtistaDTO {

    private String nomeArtista;
    private String nacionalidade;
    private String site;
    private String imagemPerfil;
    private List<MusicaDTO> musica;
}
