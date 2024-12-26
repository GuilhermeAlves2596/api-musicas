package com.api_musicas.model;

import com.api_musicas.domain.ArtistaDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaModelTest {

    @Test
    void artistaModelTest(){
        ArtistaModel artistaModel = new ArtistaModel(1L,"teste", "teste", "https://teste.com.br", "teste");

        assertEquals(1L, artistaModel.getId());
        assertEquals("teste", artistaModel.getNome());
        assertEquals("teste", artistaModel.getNacionalidade());
        assertEquals("teste", artistaModel.getImagemPerfil());
        assertEquals("https://teste.com.br", artistaModel.getSite());
    }

    @Test
    void artistaModelNoArgsTest(){
        ArtistaModel artistaModel = new ArtistaModel();
        artistaModel.setId(1L);
        artistaModel.setNome("teste");
        artistaModel.setNacionalidade("teste");
        artistaModel.setSite("https://teste.com.br");
        artistaModel.setImagemPerfil("teste");

        assertEquals(1L, artistaModel.getId());
        assertEquals("teste", artistaModel.getNome());
        assertEquals("teste", artistaModel.getNacionalidade());
        assertEquals("teste", artistaModel.getImagemPerfil());
        assertEquals("https://teste.com.br", artistaModel.getSite());
    }

    @Test
    void artistaModelBuiderTest(){
        ArtistaModel artistaModel = ArtistaModel.builder()
                .id(1L)
                .nome("teste")
                .nacionalidade("teste")
                .site("https://teste.com.br")
                .imagemPerfil("teste").build();

        assertEquals(1L, artistaModel.getId());
        assertEquals("teste", artistaModel.getNome());
        assertEquals("teste", artistaModel.getNacionalidade());
        assertEquals("teste", artistaModel.getImagemPerfil());
        assertEquals("https://teste.com.br", artistaModel.getSite());
    }

}