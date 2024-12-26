package com.api_musicas.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MusicaModelTest {

    AlbumModel albumModel;

    @BeforeEach
    void setUp() {
        albumModel = new AlbumModel(1L,"teste", 2000,"teste",new ArtistaModel());
    }

    @Test
    void musicaModelTest(){
        MusicaModel musicaModel = new MusicaModel(1L,"teste", 2, 50, 1, albumModel);

        assertEquals(1L, musicaModel.getId());
        assertEquals("teste", musicaModel.getTitulo());
        assertEquals(2, musicaModel.getMinutos());
        assertEquals(50, musicaModel.getSegundos());
        assertEquals(1, musicaModel.getNumeroFaixa());
        assertNotNull(musicaModel.getAlbum());

    }

    @Test
    void musicaModelNoArgsTest(){
        MusicaModel musicaModel = new MusicaModel();
        musicaModel.setTitulo("teste");

        assertEquals("teste", musicaModel.getTitulo());

    }

    @Test
    void musicaModelBuilderTest(){
        MusicaModel musicaModel = MusicaModel.builder()
                .titulo("teste").build();

        assertEquals("teste", musicaModel.getTitulo());

    }

}