package com.api_musicas.domain;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArtistaDTO {

    @NotBlank(message = "Erro, nome não pode ser vazio")
    private String nome;

    @NotBlank(message = "Erro, nacionalidade não pode ser vazio")
    private String nacionalidade;

    @NotBlank(message = "Erro, site não pode ser vazio")
    @URL(message = "Erro, site inválido")
    private String site;

}
