package com.api_musicas.mapper;

import com.api_musicas.domain.ArtistaDTO;
import com.api_musicas.model.ArtistaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class ArtistaMapperTest {
    @InjectMocks
    ArtistaMapper artistaMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void dtoToModelTest(){
        ArtistaDTO artistaDTO = new ArtistaDTO("teste", "teste", "https://teste.com.br");

        ArtistaModel artistaModel = artistaMapper.dtoToModel(artistaDTO);
        assertNotNull(artistaModel);
        assertEquals("teste", artistaModel.getNome());
    }

    @Test
    void dtoToModelUpdateTest(){
        ArtistaDTO artistaDTO = new ArtistaDTO("teste", "teste", "https://teste.com.br");
        ArtistaModel artistaModel = new ArtistaModel(1L, "teste", "teste", "https://teste.com.br", "teste");

        ArtistaModel artistaModelUpdate = artistaMapper.dtoToModelUpdate(artistaDTO, artistaModel);
        assertNotNull(artistaModelUpdate);
        assertEquals("teste", artistaModel.getImagemPerfil());
    }
}