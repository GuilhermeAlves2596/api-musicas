package com.api_musicas.service;

import com.api_musicas.domain.AlbumMusicasDTO;
import com.api_musicas.domain.MusicasArtistaDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.model.MusicaModel;
import com.api_musicas.repository.AlbumRepository;
import com.api_musicas.repository.MusicaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.api_musicas.util.Constantes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class MusicaServiceTest {

    @InjectMocks
    MusicaService service;
    @Mock
    MusicaRepository repository;
    @Mock
    AlbumRepository albumRepository;
    MusicaModel musicaModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        musicaModel = new MusicaModel(1L, "teste", 2,45,2, new AlbumModel());
    }

    @Test
    void saveTest(){
        when(repository.existsByAlbumIdAndNumeroFaixa(anyLong(),anyInt())).thenReturn(false);

        String resultado = service.save(musicaModel);

        assertEquals(MUSICA_CADASTRADA, resultado);
    }

    @Test
    void saveErroTest(){
        AlbumModel albumModel = new AlbumModel(1L, "teste", 2000, "teste", new ArtistaModel());
        musicaModel.setAlbum(albumModel);

        when(repository.existsByAlbumIdAndNumeroFaixa(musicaModel.getAlbum().getId(), musicaModel.getNumeroFaixa())).thenReturn(true);
        String resultado = service.save(musicaModel);

        assertEquals(ERRO_MUSICA_CADASTRADA, resultado);
    }

    @Test
    void buscarMusica(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(musicaModel));

        MusicaModel resultado = service.buscarMusica(1L);

        assertNotNull(resultado);
    }

    @Test
    void buscarMusicaExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarMusica(1L);
        });

        assertEquals(MUSICA_N_ENCONTRADA, exception.getMessage());
    }

    @Test
    void musicasTest(){
        List<MusicaModel> musicaModelList = new ArrayList<>();
        musicaModelList.add(musicaModel);
        musicaModelList.add(musicaModel);

        Pageable pageable = PageRequest.of(0, 2);
        Page<MusicaModel> musicaPage = new PageImpl<>(musicaModelList, pageable, musicaModelList.size());

        when(repository.findAll(pageable)).thenReturn(musicaPage);

        Page<MusicaModel> resultado = service.musicas(pageable);

        assertEquals(2, resultado.getTotalElements());
        assertEquals(musicaModelList, resultado.getContent());
        assertEquals(0, resultado.getNumber());
        assertEquals(2, resultado.getSize());
    }

    @Test
    void updateTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(musicaModel));

        String resultado = service.update(1L, musicaModel);
        assertEquals(MUSICA_ATUALIZADA, resultado);
    }

    @Test
    void updateExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.update(1L, musicaModel);
        });

        assertEquals(MUSICA_N_ENCONTRADA, exception.getMessage());
    }

    @Test
    void deleteTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(musicaModel));

        String resultado = service.delete(1L);

        assertEquals(MUSICA_DELETADA, resultado);
    }

    @Test
    void deleteExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(1L);
        });

        assertEquals(MUSICA_N_ENCONTRADA, exception.getMessage());
    }

    @Test
    void musicasPorArtistaTest(){
        ArtistaModel artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");
        musicaModel.setAlbum(new AlbumModel(1L,"teste", 2000,"teste",artistaModel));

        List<MusicaModel> musicaModelList = new ArrayList<>();
        musicaModelList.add(musicaModel);

        when(repository.findMusicasByArtistaId(anyLong())).thenReturn(musicaModelList);

        MusicasArtistaDTO resultado = service.musicasPorArtista(1L);

        assertNotNull(resultado);
        assertEquals("teste", resultado.getNomeArtista());
    }

    @Test
    void musicasPorArtistaExceptionTest(){

        List<MusicaModel> musicaModelList = new ArrayList<>();

        when(repository.findMusicasByArtistaId(anyLong())).thenReturn(musicaModelList);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.musicasPorArtista(1L);
        });

        assertEquals(MUSICA_ARTISTA_N_ENCONTRADA + 1L, exception.getMessage());
    }

    @Test
    void listarMusicasPorAlbumTest(){
        ArtistaModel artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");
        AlbumModel albumModel = new AlbumModel(1L,"teste", 2000,"teste",artistaModel);
        musicaModel.setAlbum(albumModel);

        List<MusicaModel> musicaModelList = new ArrayList<>();
        musicaModelList.add(musicaModel);

        when(albumRepository.findById(1L)).thenReturn(Optional.of(albumModel));
        when(repository.findMusicasByAlbumId(1L)).thenReturn(musicaModelList);

        AlbumMusicasDTO albumMusicasDTO = service.listarMusicasPorAlbum(albumModel.getId());

        assertNotNull(albumMusicasDTO);
    }

    @Test
    void listarMusicasPorAlbumExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.listarMusicasPorAlbum(1L);
        });

        assertEquals(ALBUM_N_ENCONTRADO, exception.getMessage());
    }
}