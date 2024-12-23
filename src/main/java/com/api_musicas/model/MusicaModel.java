package com.api_musicas.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

@Entity
@Data
@Builder
@Table(name = "musica")
public class MusicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String titulo;

    @Positive
    private int minutos;

    @Min(0)
    @Max(59)
    private int segundos;

    @Positive
    @Column(name = "numerofaixa")
    private int numeroFaixa;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    private AlbumModel album;
}
