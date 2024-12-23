package com.api_musicas.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@Builder
@Table(name = "artista")
public class ArtistaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String nacionalidade;

    @NotBlank
    private String site;

    @NotBlank
    private String imagemPerfil;

}
