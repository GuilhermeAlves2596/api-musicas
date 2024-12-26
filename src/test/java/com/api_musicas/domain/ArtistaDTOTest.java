package com.api_musicas.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaDTOTest {

    @Test
    void artistaDTOTest(){
        ArtistaDTO dto = new ArtistaDTO("teste", "teste", "https://teste.com.br");

        assertEquals("teste", dto.getNome());
        assertEquals("teste", dto.getNacionalidade());
        assertEquals("https://teste.com.br", dto.getSite());
    }

    @Test
    void artistaDTONoArgsTest(){
        ArtistaDTO dto = new ArtistaDTO();
        dto.setNome("teste");
        dto.setNacionalidade("teste");
        dto.setSite("https://teste.com.br");

        assertEquals("teste", dto.getNome());
        assertEquals("teste", dto.getNacionalidade());
        assertEquals("https://teste.com.br", dto.getSite());
    }

    @Test
    void artistaDTOBuiderTest(){
        ArtistaDTO dto = ArtistaDTO.builder()
                            .nome("teste")
                            .nacionalidade("teste")
                            .site("https://teste.com.br").build();

        assertEquals("teste", dto.getNome());
        assertEquals("teste", dto.getNacionalidade());
        assertEquals("https://teste.com.br", dto.getSite());
    }

}