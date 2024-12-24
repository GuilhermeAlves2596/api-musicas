package com.api_musicas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "album")
@AllArgsConstructor
@NoArgsConstructor
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(name = "anolancamento")
    private Integer anoLancamento;

    @Column(name = "imagemcapa")
    private String imagemCapa;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private ArtistaModel artista;
}
