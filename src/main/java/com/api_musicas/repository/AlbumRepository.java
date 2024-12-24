package com.api_musicas.repository;

import com.api_musicas.model.AlbumModel;
import com.api_musicas.model.ArtistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<AlbumModel, Long> {
    List<AlbumModel> findByArtistaId(Long artistaId);
}
