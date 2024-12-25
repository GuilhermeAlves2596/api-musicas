package com.api_musicas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;

@Data
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
