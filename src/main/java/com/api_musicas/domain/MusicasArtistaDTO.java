package com.api_musicas.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
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
