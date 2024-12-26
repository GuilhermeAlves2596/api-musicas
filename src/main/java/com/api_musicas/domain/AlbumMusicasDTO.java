package com.api_musicas.domain;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlbumMusicasDTO {

    private String nomeAlbum;
    private List<MusicaDTO> musicas;
}
