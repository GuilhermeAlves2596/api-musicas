package com.api_musicas.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class ImagemService {

    public String imagem(String table, MultipartFile imagem ) throws IOException {

        String uploadDir = String.format("uploads/%s/", table);
        String fileName = UUID.randomUUID() + "_" + imagem.getOriginalFilename();
        Path caminho = Paths.get(uploadDir + fileName);
        Files.createDirectories(caminho.getParent());
        Files.write(caminho, imagem.getBytes());

        return caminho.toString();
    }
}
