package com.api_musicas.domain;

import com.api_musicas.model.ArtistaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MusicaDTOTest {

    AlbumDTO albumDTO;

    @BeforeEach
    void setUp() {
        albumDTO = new AlbumDTO("teste", 2000,"teste",new ArtistaModel());
    }

    @Test
    void musicaDtoTest(){
        MusicaDTO musicaDTO = new MusicaDTO("teste", 2, 50, 1, albumDTO);

        assertEquals("teste", musicaDTO.getTituloMusica());
        assertEquals(2, musicaDTO.getMinutos());
        assertEquals(50, musicaDTO.getSegundos());
        assertEquals(1, musicaDTO.getNumeroFaixa());
        assertNotNull(musicaDTO.getAlbum());

    }

    @Test
    void musicaDtoNoArgsTest(){
        MusicaDTO musicaDTO = new MusicaDTO();
        musicaDTO.setTituloMusica("teste");

        assertEquals("teste", musicaDTO.getTituloMusica());

    }

    @Test
    void musicaDtoBuilderTest(){
        MusicaDTO musicaDTO = MusicaDTO.builder()
                        .tituloMusica("teste").build();

        assertEquals("teste", musicaDTO.getTituloMusica());

    }

    @Test
    void musicaDtoConstructorTest(){
        MusicaDTO musicaDTO = new MusicaDTO("teste", 2, 50, 1);

        assertEquals("teste", musicaDTO.getTituloMusica());
        assertEquals(2, musicaDTO.getMinutos());
        assertEquals(50, musicaDTO.getSegundos());
        assertEquals(1, musicaDTO.getNumeroFaixa());

    }
}