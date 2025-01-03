package com.api_musicas.service;

import com.api_musicas.domain.AlbumDTO;
import com.api_musicas.mapper.AlbumMapper;
import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.MusicaModel;
import com.api_musicas.repository.AlbumRepository;
import com.api_musicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static com.api_musicas.util.Constantes.*;

@Service
public class AlbumService {

    @Autowired
    private AlbumMapper mapper;
    @Autowired
    private AlbumRepository repository;
    @Autowired
    private MusicaRepository musicaRepository;
    @Autowired
    private ImagemService imagemService;

    public String save(AlbumDTO dto) {

        if(validaAno(dto.getAnoLancamento())){
            AlbumModel albumModel = mapper.dtoToModel(dto);

            repository.save(albumModel);

            return ALBUM_CADASTRADO;
        }

        return ERRO_ALBUM_CADASTRADO;

    }

    public String saveImagem(Long id, MultipartFile imagem) throws IOException {

        AlbumModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ALBUM_N_ENCONTRADO));

        model.setImagemCapa(imagemService.imagem("album", imagem));

        repository.save(model);

        return IMAGEM_CADASTRADA;

    }

    public AlbumModel buscarAlbum(Long id)  {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ALBUM_N_ENCONTRADO));

    }

    public Page<AlbumModel> albuns(Pageable pageable){
        Page<AlbumModel> albuns = repository.findAll(pageable);

        if(albuns.isEmpty()){
            throw new RuntimeException(ERRO_LISTAR_ALBUNS);
        }
        return albuns;
    }

    public String update(Long id, AlbumDTO album){
        AlbumModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ALBUM_N_ENCONTRADO));

        if(validaAno(album.getAnoLancamento())){
            repository.save(mapper.dtoToModelUpdate(album, model));

            return ALBUM_ATUALIZADO;
        }

        return ERRO_ALBUM_CADASTRADO;

    }

    public String delete(Long id){
        AlbumModel model = repository.findById(id)
                .orElseThrow(() -> new RuntimeException(ALBUM_N_ENCONTRADO));

        List<MusicaModel> musicaModel = musicaRepository.findByAlbumId(id);

        if(musicaModel.isEmpty()){
            repository.delete(model);

            return ALBUM_DELETADO;
        }

        return ERRO_ALBUM_DELETADO;

    }

    public boolean validaAno(int anoAlbum){
        LocalDate date = LocalDate.now();

        if(anoAlbum > date.getYear()){
            return false;
        }

        return true;
    }
}
