package com.api_musicas.repository;

import com.api_musicas.model.MusicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicaRepository extends JpaRepository<MusicaModel, Long> {
    List<MusicaModel> findByAlbumId(Long albumId);

    @Query("SELECT m FROM MusicaModel m " +
            "JOIN FETCH m.album a " +
            "JOIN FETCH a.artista ar " +
            "WHERE ar.id = :artistaId")
    List<MusicaModel> findMusicasByArtistaId(@Param("artistaId") Long artistaId);

    @Query("SELECT m FROM MusicaModel m WHERE m.album.id = :albumId ORDER BY m.numeroFaixa ASC")
    List<MusicaModel> findMusicasByAlbumId(@Param("albumId") Long albumId);

    boolean existsByAlbumIdAndNumeroFaixa(Long albumId, Integer numeroFaixa);
}
