package com.api_musicas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbumMusicasDTOTest {

    MusicaDTO musicaDTO;

    @BeforeEach
    void setUp() {
        musicaDTO = new MusicaDTO("teste", 2, 50, 1, new AlbumDTO());
    }

    @Test
    void albumMusicasTest(){
        List<MusicaDTO> musicaDTOList = new ArrayList<>();
        musicaDTOList.add(musicaDTO);

        AlbumMusicasDTO albumMusicasDTO = new AlbumMusicasDTO("teste", musicaDTOList);

        assertEquals("teste", albumMusicasDTO.getNomeAlbum());
        assertEquals("teste", albumMusicasDTO.getMusicas().get(0).getTituloMusica());
    }

    @Test
    void albumMusicasNoArgsTest(){
        AlbumMusicasDTO albumMusicasDTO = new AlbumMusicasDTO();
        albumMusicasDTO.setNomeAlbum("teste");

        assertEquals("teste", albumMusicasDTO.getNomeAlbum());
    }

    @Test
    void albumMusicasBuilderTest(){
        AlbumMusicasDTO albumMusicasDTO = AlbumMusicasDTO.builder()
                .nomeAlbum("teste")
                .musicas(new ArrayList<>()).build();

        assertEquals("teste", albumMusicasDTO.getNomeAlbum());
    }

}