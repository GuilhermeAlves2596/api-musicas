package com.api_musicas.controller;

import com.api_musicas.domain.AlbumMusicasDTO;
import com.api_musicas.domain.MusicasArtistaDTO;
import com.api_musicas.model.MusicaModel;
import com.api_musicas.service.MusicaService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/musica")
public class MusicaController {
    @Autowired
    private MusicaService service;

    @PostMapping(value = "/salvar")
    public ResponseEntity<String> salvarMusica(@RequestBody @Valid MusicaModel musicaModel)  {

        return ResponseEntity.ok(service.save(musicaModel));
    }

    @GetMapping("/{id}")
    public ResponseEntity<MusicaModel> getMusica(@PathVariable Long id) {

        return ResponseEntity.ok(service.buscarMusica(id));

    }

    @GetMapping
    public ResponseEntity<Page<MusicaModel>> musicas(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String direction) {

        Sort sortOrder = Sort.by(Sort.Direction.fromString(direction), sort);
        Pageable pageable = PageRequest.of(page, size, sortOrder);

        return ResponseEntity.ok(service.musicas(pageable));
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @Valid @RequestBody MusicaModel model){

        return ResponseEntity.ok(service.update(id, model));

    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){

        return ResponseEntity.ok(service.delete(id));

    }

    @GetMapping("/artista/{artistaId}")
    public ResponseEntity<MusicasArtistaDTO> listarMusicasPorArtista(@PathVariable Long artistaId) {
        MusicasArtistaDTO dto = service.musicasPorArtista(artistaId);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/album/{albumId}")
    public ResponseEntity<AlbumMusicasDTO> listarMusicasPorAlbum(@PathVariable Long albumId) {
        AlbumMusicasDTO albumMusicasDTO = service.listarMusicasPorAlbum(albumId);
        return ResponseEntity.ok(albumMusicasDTO);
    }

}
