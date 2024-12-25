package com.api_musicas.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Data
@Builder
@Table(name = "musica")
@AllArgsConstructor
@NoArgsConstructor
public class MusicaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Titulo não pode ser vazio.")
    private String titulo;

    @Positive
    @NotNull(message = "O campo minutos não pode estar vazio.")
    private Integer minutos;

    @Min(0)
    @Max(59)
    @NotNull(message = "O campo segundos não pode estar vazio.")
    private Integer segundos;

    @Positive
    @NotNull(message = "O campo numero da faixa não pode estar vazio.")
    @Column(name = "numerofaixa")
    private Integer numeroFaixa;

    @ManyToOne
    @JoinColumn(name = "album_id", nullable = false)
    @NotNull(message = "Informe o album da musica.")
    private AlbumModel album;
}
