package com.api_musicas.controller;

import com.api_musicas.domain.AlbumMusicasDTO;
import com.api_musicas.domain.MusicasArtistaDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.MusicaModel;
import com.api_musicas.service.MusicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
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

class MusicaControllerTest {

    @InjectMocks
    MusicaController controller;
    @Mock
    MusicaService service;
    private MockMvc mockMvc;
    MusicaModel model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        model = new MusicaModel(1L,"teste", 2, 25, 2, new AlbumModel());
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .build();
    }

    @Test
    void salvarMusicaTest() throws Exception {
        when(service.save(Mockito.<MusicaModel>any())).thenReturn(MUSICA_CADASTRADA);

        String content = new ObjectMapper().writeValueAsString(model);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/musica/salvar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(MUSICA_CADASTRADA));

    }

    @Test
    void getMusicaTest() throws Exception {
        when(service.buscarMusica(anyLong())).thenReturn(Mockito.any(MusicaModel.class));

        mockMvc.perform(get("/musica/1"))
                .andExpect(status().isOk());
    }

    @Test
    void musicasTest() throws Exception {
        when(service.musicas(Mockito.any(Pageable.class))).thenReturn(Mockito.any());

        mockMvc.perform(get("/musica"))
                .andExpect(status().isOk());
    }

    @Test
    void updateTest() throws Exception {
        when(service.update(anyLong(), Mockito.<MusicaModel>any())).thenReturn(MUSICA_ATUALIZADA);

        String content = new ObjectMapper().writeValueAsString(model);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/musica/atualizar/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        MockMvcBuilders.standaloneSetup(controller)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(MUSICA_ATUALIZADA));
    }

    @Test
    void deleteTest() throws Exception {
        when(service.delete(anyLong())).thenReturn(MUSICA_DELETADA);

        mockMvc.perform(MockMvcRequestBuilders.delete("/musica/deletar/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(MUSICA_DELETADA));
    }

    @Test
    void listarMusicasPorArtista() throws Exception {
        when(service.musicasPorArtista(anyLong())).thenReturn(Mockito.any(MusicasArtistaDTO.class));

        mockMvc.perform(get("/musica/artista/1"))
                .andExpect(status().isOk());
    }

    @Test
    void listarMusicasPorAlbum() throws Exception {
        when(service.listarMusicasPorAlbum(anyLong())).thenReturn(Mockito.any(AlbumMusicasDTO.class));

        mockMvc.perform(get("/musica/album/1"))
                .andExpect(status().isOk());
    }

}