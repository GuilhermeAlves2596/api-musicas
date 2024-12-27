# API de Gestão de Músicas

## Descrição

Esta aplicação é uma API desenvolvida em Java usando o framework Spring Boot para gerenciar músicas, álbuns e artistas. Ela permite realizar operações CRUD (Create, Read, Update, Delete) e inclui funcionalidades adicionais, como paginação, validação de dados e integração com Swagger para documentação.

---

## Funcionalidades

- **Gerenciamento de Artistas**: Criação, listagem, edição e exclusão de artistas.
- **Gerenciamento de Álbuns**: Associação de álbuns a artistas, validação de dados e consulta por ID.
- **Gerenciamento de Músicas**: Adição de músicas a álbuns, verificação de faixas duplicadas, ordenação e validações.
- **Documentação via Swagger**: Interface visual para explorar e testar os endpoints da API.
- **Mensagens de Erro Personalizadas**: Respostas claras em casos de erro de validação ou exceções.

---

## Tecnologias Utilizadas

- **Java 11**
- **Spring Boot**
    - Spring Data JPA
    - Spring Validation
    - Springdoc OpenAPI
- **Banco de Dados**: MySQL
- **Testes**: JUnit e MockMvc
- **Documentação**: Swagger

---

## Como Rodar a Aplicação

Requisitos
1. Java 11
2. Maven
3. Docker


### Rodando no Docker

É possível executar a aplicação utilizando o Docker através do Docker Compose. Certifique-se de que o Docker e o Docker Compose estão instalados em sua máquina. Siga os passos abaixo:

1. Execute o comando na raiz do projeto
   ```bash
   mvn clean package
   ```

2. Execute o seguinte comando para criar o container da aplicação:
   ```bash
   docker-compose up --build
   ```
3. A aplicação estará disponível em: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Estrutura do Projeto

- **Controller**: Contém as rotas e a lógica de entrada/saída da aplicação.
- **Service**: Contém a lógica de negócio.
- **Repository**: Camada de persistência com Spring Data JPA.
- **Model**: Entidades representando as tabelas do banco de dados.
- **DTO**: Objetos de transferência de dados para as respostas da API.
- **Exception Handler**: Gerencia as exceções e fornece respostas claras ao cliente.

---

## Endpoints Principais

### Artistas

- **GET /artista**: Lista todos os artistas.
- **GET /artista/{id}**: Busca um artista por id.
- **POST /artista/salvar**: Cria um novo artista.
- **POST /artista/salvar/{id}/imagem**: Cadastra uma imagem no artista (a imagem deve estar no formato .jpg).
- **PUT /artista/atualizar/{id}**: Atualiza um artista existente.
- **DELETE /artista/deletar/{id}**: Remove um artista.

### Álbuns

- **GET /album**: Lista todos os álbuns.
- **GET /album/{id}**: Busca um álbum por ID.
- **POST /album/salvar**: Cria um novo álbum.
- **POST /album/salvar/{id}/imagem**: Cadastra uma imagem no album (a imagem deve estar no formato .jpg).
- **PUT /album/atualizar/{id}**: Atualiza um album existente.
- **DELETE /album/deletar/{id}**: Remove um álbum.

### Músicas

- **GET /musica**: Lista todas as músicas com paginação.
- **GET /musica/{id}**: Busca uma música por ID.
- **GET /musica/artista/{artistaId}**: Busca as musicas de um artista por id.
- **GET /musica/album/{albumId}**: Busca as musicas de um album por id.
- **POST /musica/salvar**: Adiciona uma nova música.
- **PUT /musica/atualizar/{id}**: Atualiza uma música.
- **DELETE /musica/deletar/{id}**: Remove uma música.

---

## Testes

Para executar os testes, utilize o comando:

```bash
mvn test
```

Os testes abrangem:

- Validações de entrada (campos obrigatórios, faixas duplicadas, etc.).
- Lógicas de negócio.
- Respostas da API em caso de erros.

---

## Coleção Postman

Uma coleção Postman contendo os endpoints da API está disponível no arquivo `API-Musicas.postman_collection` no diretório do projeto. Importe-a para testar a aplicação com facilidade.

---

## Contribuições

Sinta-se à vontade para abrir issues e pull requests para melhorias na aplicação.

---

## Autor

Desenvolvido por **Guilherme de Andrade Alves**.

