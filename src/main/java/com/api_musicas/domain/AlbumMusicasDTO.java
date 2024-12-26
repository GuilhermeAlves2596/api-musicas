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
public class AlbumMusicasDTO {

    private String nomeAlbum;
    private List<MusicaDTO> musicas;
}
