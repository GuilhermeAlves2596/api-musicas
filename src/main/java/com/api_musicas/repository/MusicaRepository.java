package com.api_musicas.repository;

import com.api_musicas.model.MusicaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MusicaRepository extends JpaRepository<MusicaModel, Long> {
}
