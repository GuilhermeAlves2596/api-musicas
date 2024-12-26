package com.api_musicas.model;

import com.api_musicas.domain.AlbumDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumModelTest {

    ArtistaModel artistaModel;

    @BeforeEach
    void setUp() {
        artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");
    }

    @Test
    void albumModelTest(){

        AlbumModel albumModel = new AlbumModel();
        albumModel.setId(1L);
        albumModel.setTitulo("teste");
        albumModel.setAnoLancamento(2000);
        albumModel.setImagemCapa("teste");
        albumModel.setArtista(artistaModel);

        assertEquals(1L, albumModel.getId());
        assertEquals("teste", albumModel.getTitulo());
        assertEquals(2000, albumModel.getAnoLancamento());
        assertEquals("teste", albumModel.getImagemCapa());
        assertNotNull(albumModel.getArtista());
    }

    @Test
    void albumModelAllArgsTest(){

        AlbumModel albumModel = new AlbumModel(1L,"teste", 2000,"teste",artistaModel);

        assertEquals(1L, albumModel.getId());
        assertEquals("teste", albumModel.getTitulo());
        assertEquals(2000, albumModel.getAnoLancamento());
        assertEquals("teste", albumModel.getImagemCapa());
        assertNotNull(albumModel.getArtista());
    }

    @Test
    void albumDTOBuilderTest(){

        AlbumModel albumModel = AlbumModel.builder()
                .id(1L)
                .titulo("teste")
                .anoLancamento(2000)
                .imagemCapa("teste")
                .artista(artistaModel).build();

        assertEquals(1L, albumModel.getId());
        assertEquals("teste", albumModel.getTitulo());
        assertEquals(2000, albumModel.getAnoLancamento());
        assertEquals("teste", albumModel.getImagemCapa());
        assertNotNull(albumModel.getArtista());
    }


}