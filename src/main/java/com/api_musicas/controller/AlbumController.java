package com.api_musicas.controller;

import com.api_musicas.domain.dto.AlbumDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.service.AlbumService;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/album")
@Data
public class AlbumController {

    private final AlbumService service;

    @PostMapping(value = "/salvar")
    public ResponseEntity<String> salvarAlbum(@RequestBody @Valid AlbumDTO albumDTO)  {

        return ResponseEntity.ok(service.save(albumDTO));
    }

    @PostMapping(value = "/salvar/{id}/imagem", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> salvarImagemAlbum(@PathVariable Long id, @RequestPart("imagem") MultipartFile imagem) throws IOException {

        return ResponseEntity.ok(service.saveImagem(id, imagem));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumModel> getAlbum(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscarAlbum(id));

    }

    @GetMapping
    public ResponseEntity<Page<AlbumModel>> albuns(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return ResponseEntity.ok(service.albuns(pageable));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody AlbumDTO dto){

        return ResponseEntity.ok(service.update(id, dto));

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        return ResponseEntity.ok(service.delete(id));

    }

}
