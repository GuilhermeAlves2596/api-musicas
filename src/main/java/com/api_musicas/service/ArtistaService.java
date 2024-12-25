package com.api_musicas.service;

import com.api_musicas.domain.dto.ArtistaDTO;
import com.api_musicas.mapper.ArtistaMapper;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import com.api_musicas.repository.AlbumRepository;
import com.api_musicas.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.api_musicas.util.Constantes.*;

@Service
public class ArtistaService {

    @Autowired
    private ArtistaMapper mapper;
    @Autowired
    private ArtistaRepository repository;
    @Autowired
    private ImagemService imagemService;
    @Autowired
    private AlbumRepository albumRepository;

    public String save(ArtistaDTO dto) {
        ArtistaModel artistaModel = mapper.dtoToModel(dto);

        repository.save(artistaModel);

        return ARTISTA_CADASTRADO;
    }

    public String saveImagem(Long id, MultipartFile imagem) throws IOException {

        ArtistaModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ARTISTA_N_ENCONTRADO));

        model.setImagemPerfil(imagemService.imagem("artista", imagem));

        repository.save(model);

        return IMAGEM_CADASTRADA;

    }

    public ArtistaModel buscarArtista(Long id)  {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ARTISTA_N_ENCONTRADO));

    }

    public Page<ArtistaModel> artistas(Pageable pageable){
        return repository.findAll(pageable);
    }

    public String update(Long id, ArtistaDTO artista){
        ArtistaModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ARTISTA_N_ENCONTRADO));

        repository.save(mapper.dtoToModelUpdate(artista, model));

        return ARTISTA_ATUALIZADO;

    }

    public String delete(Long id){
        ArtistaModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ARTISTA_N_ENCONTRADO));

        List<AlbumModel> albumModel = albumRepository.findByArtistaId(id);

        if(albumModel.isEmpty()){
            repository.delete(model);

            return ARTISTA_DELETADO;
        }

        return ERRO_ARTISTA_DELETADO;
    }
}
