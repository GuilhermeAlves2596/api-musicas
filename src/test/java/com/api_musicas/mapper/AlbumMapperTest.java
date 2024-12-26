package com.api_musicas.mapper;

import com.api_musicas.domain.AlbumDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class AlbumMapperTest {
    @InjectMocks
    AlbumMapper albumMapper;

    ArtistaModel artistaModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");
    }

    @Test
    void dtoToModelTest(){
        AlbumDTO albumDTO = new AlbumDTO("teste", 2000, "teste", artistaModel);

        AlbumModel albumModel = albumMapper.dtoToModel(albumDTO);
        assertNotNull(albumModel);
        assertEquals("teste", albumModel.getTitulo());
    }

    @Test
    void dtoToModelUpdateTest(){
        AlbumDTO albumDTO = new AlbumDTO("teste", 2000, "teste", artistaModel);
        AlbumModel albumModel = new AlbumModel(1L, "teste", 2000, "teste", artistaModel);

        AlbumModel albumUpdate = albumMapper.dtoToModelUpdate(albumDTO, albumModel);

        assertNotNull(albumUpdate);
        assertEquals("teste", albumModel.getTitulo());
    }

}