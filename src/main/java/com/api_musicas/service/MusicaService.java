package com.api_musicas.service;

import com.api_musicas.domain.AlbumDTO;
import com.api_musicas.domain.AlbumMusicasDTO;
import com.api_musicas.domain.MusicaDTO;
import com.api_musicas.domain.MusicasArtistaDTO;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.model.MusicaModel;
import com.api_musicas.repository.AlbumRepository;
import com.api_musicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.api_musicas.util.Constantes.*;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository repository;
    @Autowired
    private AlbumRepository albumRepository;

    public String save(MusicaModel musica) {

        if(repository.existsByAlbumIdAndNumeroFaixa(musica.getAlbum().getId(), musica.getNumeroFaixa())){
            return ERRO_MUSICA_CADASTRADA;
        }

        repository.save(musica);

        return MUSICA_CADASTRADA;

    }

    public MusicaModel buscarMusica(Long id)  {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(MUSICA_N_ENCONTRADA));

    }

    public Page<MusicaModel> musicas(Pageable pageable){
        Page<MusicaModel> musicas = repository.findAll(pageable);

        if(musicas.isEmpty()){
            throw new RuntimeException(ERRO_LISTAR_MUSICAS);
        }

        return musicas;
    }

    public String update(Long id, MusicaModel musica){
        repository.findById(id)
                .orElseThrow(() -> new RuntimeException(MUSICA_N_ENCONTRADA));

            musica.setId(id);
            repository.save(musica);

            return MUSICA_ATUALIZADA;

    }

    public String delete(Long id){
        MusicaModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(MUSICA_N_ENCONTRADA));

        repository.delete(model);

        return MUSICA_DELETADA;

    }

    public MusicasArtistaDTO musicasPorArtista(Long id){
        List<MusicaModel> musicasArtistaList = repository.findMusicasByArtistaId(id);

        if (musicasArtistaList.isEmpty()) {
            throw new RuntimeException(MUSICA_ARTISTA_N_ENCONTRADA + id);
        }

        return montarArtistaEMusicas(musicasArtistaList);
    }

    public AlbumMusicasDTO listarMusicasPorAlbum(Long albumId) {

        AlbumModel album = albumRepository.findById(albumId)
                .orElseThrow(() -> new RuntimeException(ALBUM_N_ENCONTRADO));

        List<MusicaModel> musicas = repository.findMusicasByAlbumId(albumId);

        List<MusicaDTO> musicaDTOs = musicas.stream()
                .map(musica -> new MusicaDTO(
                        musica.getTitulo(),
                        musica.getMinutos(),
                        musica.getSegundos(),
                        musica.getNumeroFaixa()
                ))
                .collect(Collectors.toList());

        return new AlbumMusicasDTO(album.getTitulo(), musicaDTOs);
    }

    public MusicasArtistaDTO montarArtistaEMusicas(List<MusicaModel> musicas) {
        ArtistaModel artista = musicas.get(0).getAlbum().getArtista();

        MusicasArtistaDTO musicasArtistaDTO = new MusicasArtistaDTO();
        musicasArtistaDTO.setNomeArtista(artista.getNome());
        musicasArtistaDTO.setNacionalidade(artista.getNacionalidade());
        musicasArtistaDTO.setSite(artista.getSite());
        musicasArtistaDTO.setImagemPerfil(artista.getImagemPerfil());

        List<MusicaDTO> musicaDTOList = musicas.stream().map(musica -> {
            AlbumDTO albumDTO = new AlbumDTO();
            albumDTO.setTitulo(musica.getAlbum().getTitulo());
            albumDTO.setAnoLancamento(musica.getAlbum().getAnoLancamento());
            albumDTO.setImagemCapa(musica.getAlbum().getImagemCapa());

            MusicaDTO musicaDTO = new MusicaDTO();
            musicaDTO.setTituloMusica(musica.getTitulo());
            musicaDTO.setMinutos(musica.getMinutos());
            musicaDTO.setSegundos(musica.getSegundos());
            musicaDTO.setNumeroFaixa(musica.getNumeroFaixa());
            musicaDTO.setAlbum(albumDTO);

            return musicaDTO;
        }).collect(Collectors.toList());

        musicasArtistaDTO.setMusica(musicaDTOList);

        return musicasArtistaDTO;
    }
}
