package com.api_musicas.domain;

import com.api_musicas.model.ArtistaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AlbumDTOTest {

    ArtistaModel artistaModel;

    @BeforeEach
    void setUp() {
        artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");
    }

    @Test
    void albumDTOTest(){

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setTitulo("teste");
        albumDTO.setAnoLancamento(2000);
        albumDTO.setImagemCapa("teste");
        albumDTO.setArtista(artistaModel);

        assertEquals("teste", albumDTO.getTitulo());
        assertEquals(2000, albumDTO.getAnoLancamento());
        assertEquals("teste", albumDTO.getImagemCapa());
        assertNotNull(albumDTO.getArtista());
    }

    @Test
    void albumDTOAllArgsTest(){

        AlbumDTO albumDTO = new AlbumDTO("teste", 2000,"teste",artistaModel);

        assertEquals("teste", albumDTO.getTitulo());
        assertEquals(2000, albumDTO.getAnoLancamento());
        assertEquals("teste", albumDTO.getImagemCapa());
        assertNotNull(albumDTO.getArtista());
    }

    @Test
    void albumDTOBuilderTest(){

        AlbumDTO albumDTO = AlbumDTO.builder()
                    .titulo("teste")
                    .anoLancamento(2000)
                    .imagemCapa("teste")
                    .artista(artistaModel).build();

        assertEquals("teste", albumDTO.getTitulo());
        assertEquals(2000, albumDTO.getAnoLancamento());
        assertEquals("teste", albumDTO.getImagemCapa());
        assertNotNull(albumDTO.getArtista());
    }


}