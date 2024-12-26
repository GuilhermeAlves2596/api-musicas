package com.api_musicas.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImagemServiceTest {

    @InjectMocks
    ImagemService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testImagem() throws IOException {
        String table = "teste";
        String fileName = "teste.jpg";
        byte[] fileContent = "teste".getBytes();

        MockMultipartFile imagem = new MockMultipartFile(
                "file",
                fileName,
                "image/jpg",
                fileContent
        );

        String uploadDir = String.format("uploads/%s/", table);
        Path caminhoEsperado = Paths.get(uploadDir + UUID.randomUUID() + "_" + fileName);

        Files.createDirectories(caminhoEsperado.getParent());
        Files.write(caminhoEsperado, fileContent);

        String resultado = service.imagem(table, imagem);

        assertNotNull(resultado);
        assertTrue(resultado.contains(fileName));

    }
}