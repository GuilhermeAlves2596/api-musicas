package com.api_musicas.repository;

import com.api_musicas.model.ArtistaModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistaRepository extends JpaRepository<ArtistaModel, Long> {
}
