package com.api_musicas.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

@Entity
@Data
@Builder
@Table(name = "album")
public class AlbumModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @PastOrPresent
    @Column(name = "anolancamento")
    private int anoLancamento;

    @NotBlank
    @Column(name = "imagemcapa")
    private String imagemCapa;

    @ManyToOne
    @JoinColumn(name = "artista_id", nullable = false)
    private ArtistaModel artista;
}
