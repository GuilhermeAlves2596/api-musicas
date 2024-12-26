package com.api_musicas.domain;

import com.api_musicas.model.ArtistaModel;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlbumDTO {

    @NotBlank(message = "Titulo não pode ser vazio.")
    private String titulo;

    @NotNull(message = "O campo ano não pode estar vazio.")
    private Integer anoLancamento;

    private String imagemCapa;

    @NotNull(message = "Informe o artista do album.")
    private ArtistaModel artista;
}
