package com.api_musicas.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicasArtistaDTOTest {
    MusicaDTO musicaDTO;

    @BeforeEach
    void setUp() {
        musicaDTO = new MusicaDTO("teste", 2, 50, 1, new AlbumDTO());
    }
    @Test
    void musicaArtistaDtoTest(){
        List<MusicaDTO> musicaDTOList = new ArrayList<>();
        musicaDTOList.add(musicaDTO);

        MusicasArtistaDTO artistaDTO = new MusicasArtistaDTO("teste", "teste", "teste", "teste", musicaDTOList);

        assertEquals("teste", artistaDTO.getNomeArtista());
        assertEquals("teste", artistaDTO.getNacionalidade());
        assertEquals("teste", artistaDTO.getSite());
        assertEquals("teste", artistaDTO.getImagemPerfil());
        assertEquals("teste", artistaDTO.getMusica().get(0).getTituloMusica());
    }

    @Test
    void musicaArtistaDtoNoArgsTest(){
        MusicasArtistaDTO artistaDTO = new MusicasArtistaDTO();
        artistaDTO.setNomeArtista("teste");

        assertEquals("teste", artistaDTO.getNomeArtista());

    }

    @Test
    void musicaArtistaDtoBuilderTest(){
        MusicasArtistaDTO artistaDTO = MusicasArtistaDTO.builder()
                        .nomeArtista("teste")
                        .nacionalidade("teste")
                        .site("teste")
                        .imagemPerfil("teste").musica(new ArrayList<>()).build();

        assertEquals("teste", artistaDTO.getNomeArtista());

    }

}