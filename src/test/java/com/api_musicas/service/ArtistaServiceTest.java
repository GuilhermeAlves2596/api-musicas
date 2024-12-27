package com.api_musicas.service;

import com.api_musicas.domain.ArtistaDTO;
import com.api_musicas.mapper.ArtistaMapper;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.repository.AlbumRepository;
import com.api_musicas.repository.ArtistaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static com.api_musicas.util.Constantes.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class ArtistaServiceTest {

    @InjectMocks
    ArtistaService service;
    @Mock
    ArtistaMapper artistaMapper;
    @Mock
    ImagemService imagemService;
    @Mock
    ArtistaRepository repository;
    @Mock
    AlbumRepository albumRepository;

    ArtistaDTO artistaDTO;
    ArtistaModel artistaModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        artistaDTO = new ArtistaDTO("teste", "teste", "https://teste.com.br");
        artistaModel = new ArtistaModel(1L, "teste", "teste", "https://teste.com.br", "teste");
    }

    @Test
    void saveTest(){
        String resultado = service.save(artistaDTO);

        assertEquals(ARTISTA_CADASTRADO, resultado);
    }

    @Test
    void saveImagemTest() throws IOException {

        when(repository.findById(anyLong())).thenReturn(Optional.of(artistaModel));

        String resultado = service.saveImagem(1L, new MockMultipartFile("teste", "teste".getBytes()));

        assertEquals(IMAGEM_CADASTRADA, resultado);
    }

    @Test
    void saveImagemExceptionTest() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.saveImagem(1L, new MockMultipartFile("teste", "teste".getBytes()));
        });

        assertEquals(ARTISTA_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void buscarArtistaTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(artistaModel));

        ArtistaModel resultado = service.buscarArtista(1L);
        assertNotNull(resultado);
    }

    @Test
    void buscarArtistaExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarArtista(1L);
        });

        assertEquals(ARTISTA_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void artistasTest(){
        List<ArtistaModel> artistaModelList = new ArrayList<>();
        artistaModelList.add(artistaModel);
        artistaModelList.add(artistaModel);

        Pageable pageable = PageRequest.of(0, 2);
        Page<ArtistaModel> artistaPage = new PageImpl<>(artistaModelList, pageable, artistaModelList.size());

        when(repository.findAll(pageable)).thenReturn(artistaPage);

        Page<ArtistaModel> resultado = service.artistas(pageable);

        assertEquals(2, resultado.getTotalElements());
        assertEquals(artistaModelList, resultado.getContent());
        assertEquals(0, resultado.getNumber());
        assertEquals(2, resultado.getSize());
    }

    @Test
    void artistasErroTest(){
        List<ArtistaModel> artistaModelList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 2);
        Page<ArtistaModel> artistaPage = new PageImpl<>(artistaModelList, pageable, 0);

        when(repository.findAll(pageable)).thenReturn(artistaPage);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.artistas(pageable);
        });

        assertEquals(ERRO_LISTAR_ARTISTAS, exception.getMessage());
    }

    @Test
    void updateTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(artistaModel));

        String resultado = service.update(1L, artistaDTO);

        assertEquals(ARTISTA_ATUALIZADO, resultado);
    }

    @Test
    void updateExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.update(1L, artistaDTO);
        });

        assertEquals(ARTISTA_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void deleteTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(artistaModel));
        when(albumRepository.findByArtistaId(anyLong())).thenReturn(anyList());

        String resultado = service.delete(1L);
        assertEquals(ARTISTA_DELETADO, resultado);
    }

    @Test
    void deleteExeptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(1L);
        });

        assertEquals(ARTISTA_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void deleteErroTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(artistaModel));
        List<AlbumModel> albumModelList = new ArrayList<>();
        albumModelList.add(new AlbumModel(1L,"teste", 2000,"teste",artistaModel));
        when(albumRepository.findByArtistaId(anyLong())).thenReturn(albumModelList);

        String resultado = service.delete(1L);
        assertEquals(ERRO_ARTISTA_DELETADO, resultado);
    }
}