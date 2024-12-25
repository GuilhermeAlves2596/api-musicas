package com.api_musicas.service;

import com.api_musicas.model.MusicaModel;
import com.api_musicas.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static com.api_musicas.util.Constantes.*;

@Service
public class MusicaService {

    @Autowired
    private MusicaRepository repository;

    public String save(MusicaModel musica) {

        repository.save(musica);

        return MUSICA_CADASTRADA;

    }

    public MusicaModel buscarMusica(Long id)  {

        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException(MUSICA_N_ENCONTRADA));

    }

    public Page<MusicaModel> musicas(Pageable pageable){
        return repository.findAll(pageable);
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
}
