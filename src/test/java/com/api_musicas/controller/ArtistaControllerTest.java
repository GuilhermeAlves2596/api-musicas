package com.api_musicas.controller;

import com.api_musicas.domain.ArtistaDTO;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.service.ArtistaService;
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

class ArtistaControllerTest {
    @InjectMocks
    ArtistaController controller;
    @Mock
    ArtistaService service;
    private MockMvc mockMvc;
    ArtistaDTO dto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        dto = new ArtistaDTO("teste", "teste", "https://teste.com.br");
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void salvarArtistaTest() throws Exception {
        when(service.save(Mockito.<ArtistaDTO>any())).thenReturn(ARTISTA_CADASTRADO);

        String content = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/artista/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(ARTISTA_CADASTRADO));

    }

    @Test
    void salvarImagemArtistaTest() throws Exception {

        MockMultipartFile mockFile = new MockMultipartFile(
                "imagem",
                "teste.jpg",
                "image/jpg",
                "teste".getBytes()
        );

        when(service.saveImagem(Mockito.eq(1L), Mockito.any(MockMultipartFile.class))).thenReturn(IMAGEM_CADASTRADA);

        mockMvc.perform(MockMvcRequestBuilders.multipart("/artista/salvar/1/imagem")
                        .file(mockFile)
                        .contentType(MediaType.MULTIPART_FORM_DATA_VALUE))
                .andExpect(status().isOk())
                .andExpect(content().string(IMAGEM_CADASTRADA));
    }

    @Test
    void getArtistaTest() throws Exception {
        when(service.buscarArtista(anyLong())).thenReturn(Mockito.any(ArtistaModel.class));

        mockMvc.perform(get("/artista/1"))
                .andExpect(status().isOk());
    }

    @Test
    void artistasTest() throws Exception {
        when(service.artistas(Mockito.any(Pageable.class))).thenReturn(Mockito.any());

        mockMvc.perform(get("/artista"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        when(service.update(anyLong(), Mockito.<ArtistaDTO>any())).thenReturn(ARTISTA_ATUALIZADO);

        String content = new ObjectMapper().writeValueAsString(dto);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/artista/atualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(ARTISTA_ATUALIZADO));
    }

    @Test
    void deleteTest() throws Exception {
        when(service.delete(anyLong())).thenReturn(ARTISTA_DELETADO);

        mockMvc.perform(MockMvcRequestBuilders.delete("/artista/deletar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(ARTISTA_DELETADO));
    }
}