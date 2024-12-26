package com.api_musicas.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Builder
@Table(name = "artista")
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "imagemperfil")
    private String imagemPerfil;

}
