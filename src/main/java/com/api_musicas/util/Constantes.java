package com.api_musicas.util;

public class Constantes {

    public static String IMAGEM_CADASTRADA = "Imagem enviada com sucesso.";

    //Artista
    public static String ARTISTA_CADASTRADO = "Artista cadastrado com sucesso.";
    public static String ARTISTA_ATUALIZADO = "Artista atualizado com sucesso.";
    public static String ARTISTA_DELETADO = "Artista excluido com sucesso.";
    public static String ERRO_ARTISTA_DELETADO = "Erro: O artista possui albuns cadastrados.";
    public static String ARTISTA_N_ENCONTRADO = "Artista não encontrado";
    public static String ERRO_LISTAR_ARTISTAS = "Erro: Não há artistas cadastrados.";

    //Album
    public static String ALBUM_CADASTRADO = "Album cadastrado com sucesso.";
    public static String ERRO_ALBUM_CADASTRADO = "Erro: Campo ano não pode ser superior ao ano atual.";
    public static String ALBUM_ATUALIZADO = "Album atualizado com sucesso.";
    public static String ALBUM_DELETADO = "Album excluido com sucesso.";
    public static String ALBUM_N_ENCONTRADO = "Album não encontrado";
    public static String ERRO_ALBUM_DELETADO = "Erro: O album possui musicas cadastradas.";
    public static String ERRO_LISTAR_ALBUNS = "Erro: Não há albuns cadastrados.";

    //Musica
    public static String MUSICA_CADASTRADA = "Musica cadastrada com sucesso.";
    public static String ERRO_MUSICA_CADASTRADA = "Já existe uma música com esse número de faixa no álbum.";
    public static String MUSICA_ATUALIZADA = "Musica atualizada com sucesso.";
    public static String MUSICA_DELETADA = "Musica excluida com sucesso.";
    public static String MUSICA_N_ENCONTRADA = "Musica não encontrada";
    public static String MUSICA_ARTISTA_N_ENCONTRADA = "Nenhuma música encontrada para o artista com ID: ";
    public static String ERRO_LISTAR_MUSICAS = "Erro: Não há musicas cadastradas.";

}
