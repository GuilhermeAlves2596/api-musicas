package com.api_musicas.controller;

import com.api_musicas.domain.AlbumDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.service.AlbumService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import static com.api_musicas.util.Constantes.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class AlbumControllerTest {
    @InjectMocks
    AlbumController controller;
    @Mock
    AlbumService service;
    private MockMvc mockMvc;

    AlbumDTO albumDTO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        albumDTO = new AlbumDTO("teste", 2000,"teste",new ArtistaModel());
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void salvarAlbumTest() throws Exception {
        when(service.save(Mockito.<AlbumDTO>any())).thenReturn(ALBUM_CADASTRADO);

        String content = new ObjectMapper().writeValueAsString(albumDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/album/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(ALBUM_CADASTRADO));

    }

    @Test
    void salvarImagemAlbumTest() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile(
                "imagem",
                "teste.jpg",
                "image/jpg",
                "teste".getBytes()
        );

        when(service.saveImagem(Mockito.eq(1L), Mockito.any(MockMultipartFile.class))).thenReturn(IMAGEM_CADASTRADA);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/album/salvar/1/imagem")
                        .file(mockFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(IMAGEM_CADASTRADA));
    }

    @Test
    void getAlbumTest() throws Exception {
        when(service.buscarAlbum(anyLong())).thenReturn(Mockito.any(AlbumModel.class));

        mockMvc.perform(get("/album/1"))
                .andExpect(status().isOk());
    }

    @Test
    void albunsTest() throws Exception {
        when(service.albuns(Mockito.any(Pageable.class))).thenReturn(Mockito.any());

        mockMvc.perform(get("/album"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        when(service.update(anyLong(), Mockito.<AlbumDTO>any())).thenReturn(ALBUM_ATUALIZADO);

        String content = new ObjectMapper().writeValueAsString(albumDTO);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/album/atualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(ALBUM_ATUALIZADO));
    }

    @Test
    void deleteTest() throws Exception {
        when(service.delete(anyLong())).thenReturn(ALBUM_DELETADO);

        mockMvc.perform(MockMvcRequestBuilders.delete("/album/deletar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(ALBUM_DELETADO));
    }

}