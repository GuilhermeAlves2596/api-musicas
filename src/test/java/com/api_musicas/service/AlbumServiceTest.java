package com.api_musicas.service;

import com.api_musicas.domain.AlbumDTO;
import com.api_musicas.mapper.AlbumMapper;
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
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.api_musicas.util.Constantes.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class AlbumServiceTest {

    @InjectMocks
    AlbumService service;
    @Mock
    AlbumRepository repository;
    @Mock
    MusicaRepository musicaRepository;
    @Mock
    AlbumMapper albumMapper;
    @Mock
    ImagemService imagemService;

    AlbumModel albumModel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        albumModel = new AlbumModel(1L, "teste", 2000, "teste", new ArtistaModel());
    }

    @Test
    void saveTest(){
        ArtistaModel artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setTitulo("teste");
        albumDTO.setAnoLancamento(2000);
        albumDTO.setImagemCapa("teste");
        albumDTO.setArtista(artistaModel);

        String resultado = service.save(albumDTO);
        assertEquals(ALBUM_CADASTRADO, resultado);
    }

    @Test
    void saveErroTest(){
        ArtistaModel artistaModel = new ArtistaModel(1L, "teste", "teste", "teste", "teste");

        AlbumDTO albumDTO = new AlbumDTO();
        albumDTO.setTitulo("teste");
        albumDTO.setAnoLancamento(3000);
        albumDTO.setImagemCapa("teste");
        albumDTO.setArtista(artistaModel);

        String resultado = service.save(albumDTO);
        assertEquals(ERRO_ALBUM_CADASTRADO, resultado);
    }

    @Test
    void saveImagemTest() throws IOException {

        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));

        String resultado = service.saveImagem(1L, new MockMultipartFile("teste", "teste".getBytes()));

        assertEquals(IMAGEM_CADASTRADA, resultado);
    }

    @Test
    void saveImagemExceptionTest() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.saveImagem(1L, new MockMultipartFile("teste", "teste".getBytes()));
        });

        assertEquals(ALBUM_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void buscarAlbumTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));

        AlbumModel albumModel= service.buscarAlbum(1L);
        assertNotNull(albumModel);
    }

    @Test
    void buscarAlbumExceptionTest() {

        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.buscarAlbum(1L);
        });

        assertEquals(ALBUM_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void albunsTest(){
            AlbumModel album1 = new AlbumModel(1L, "teste", 2000, "teste", new ArtistaModel());
            AlbumModel album2 = new AlbumModel(2L, "teste 1", 2001, "teste 1", new ArtistaModel());
            List<AlbumModel> albumList = new ArrayList<>();
            albumList.add(album1);
            albumList.add(album2);

            Pageable pageable = PageRequest.of(0, 2);
            Page<AlbumModel> albumPage = new PageImpl<>(albumList, pageable, albumList.size());

            when(repository.findAll(pageable)).thenReturn(albumPage);

            Page<AlbumModel> resultado = service.albuns(pageable);

            assertEquals(2, resultado.getTotalElements());
            assertEquals(albumList, resultado.getContent());
            assertEquals(0, resultado.getNumber());
            assertEquals(2, resultado.getSize());
    }

    @Test
    void albunsErroTest(){
        List<AlbumModel> albumList = new ArrayList<>();

        Pageable pageable = PageRequest.of(0, 2);
        Page<AlbumModel> albumPage = new PageImpl<>(albumList, pageable, 0);

        when(repository.findAll(pageable)).thenReturn(albumPage);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.albuns(pageable);
        });

        assertEquals(ERRO_LISTAR_ALBUNS, exception.getMessage());
    }

    @Test
    void updateTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));

        String resultado = service.update(1L, new AlbumDTO("teste", 2000, "teste", new ArtistaModel()));
        assertEquals(ALBUM_ATUALIZADO, resultado);
    }

    @Test
    void updateErroTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));

        String resultado = service.update(1L, new AlbumDTO("teste", 3000, "teste", new ArtistaModel()));
        assertEquals(ERRO_ALBUM_CADASTRADO, resultado);
    }

    @Test
    void updateExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.update(1L, new AlbumDTO("teste", 3000, "teste", new ArtistaModel()));
        });

        assertEquals(ALBUM_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void deleteTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));
        when(musicaRepository.findByAlbumId(anyLong())).thenReturn(anyList());

        String resultado = service.delete(1L);
        assertEquals(ALBUM_DELETADO, resultado);
    }

    @Test
    void deleteExceptionTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.delete(1L);
        });

        assertEquals(ALBUM_N_ENCONTRADO, exception.getMessage());
    }

    @Test
    void deleteErroTest(){
        when(repository.findById(anyLong())).thenReturn(Optional.of(albumModel));
        List<MusicaModel> musicaModelList = new ArrayList<>();
        musicaModelList.add(new MusicaModel(1L, "teste", 2, 45, 2, albumModel));

        when(musicaRepository.findByAlbumId(anyLong())).thenReturn(musicaModelList);

        String resultado = service.delete(1L);
        assertEquals(ERRO_ALBUM_DELETADO, resultado);
    }

    @Test
    void validaAnoTest(){
        assertTrue(service.validaAno(2000));
    }

    @Test
    void validaAnoFalseTest(){
        assertFalse(service.validaAno(3000));
    }
}