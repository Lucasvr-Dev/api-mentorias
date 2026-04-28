# API de Mentorias

Sistema de gerenciamento de mentorias desenvolvido com Spring Boot, permitindo o cadastro de usuários (mentores e alunos) e o agendamento de sessões de mentoria.

---

## Tecnologias

- Java 21
- Spring Boot 4.0.5
- Spring Data JPA
- Spring Validation
- PostgreSQL
- Lombok
- SpringDoc OpenAPI (Swagger UI)

---

## Pré-requisitos

- Java 21+
- Maven 3.9+ (ou use o wrapper `./mvnw`)
- PostgreSQL em execução

---

## Configuração

Crie o arquivo `src/main/resources/application.properties` (ignorado pelo `.gitignore`) com as seguintes propriedades:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---

## Como executar

```bash
./mvnw spring-boot:run
```

A aplicação sobe em `http://localhost:8080`.

A documentação Swagger abre automaticamente no navegador ao iniciar. Também pode ser acessada manualmente em:

```
http://localhost:8080/swagger-ui/index.html
```

---

## Estrutura do projeto

```
src/main/java/com/exerciciogpt3/
├── config/          # Configuração do Swagger/OpenAPI e abertura automática do navegador
├── controller/      # Endpoints REST
├── dto/             # Objetos de requisição e resposta (Request/Response DTOs)
├── enums/           # Enumerações (TipoDeUsuario)
├── exceptions/      # Exceções customizadas e handler global
├── mapper/          # Conversão entre entidades e DTOs
├── model/           # Entidades JPA
├── repository/      # Interfaces Spring Data JPA
└── service/         # Regras de negócio
```

---

## Endpoints

### Usuários `/usuarios`

| Método | Rota               | Descrição                  |
|--------|--------------------|----------------------------|
| POST   | `/usuarios`        | Cria um novo usuário       |
| GET    | `/usuarios`        | Lista todos os usuários    |
| GET    | `/usuarios/{id}`   | Busca usuário por ID       |
| DELETE | `/usuarios/{id}`   | Remove um usuário          |

**Corpo da requisição (POST):**
```json
{
  "nome": "João Silva",
  "email": "joao@email.com",
  "tipo": "MENTOR"
}
```

> `tipo` aceita os valores: `MENTOR` ou `ALUNO`

---

### Mentorias `/mentorias`

| Método | Rota                             | Descrição                           |
|--------|----------------------------------|-------------------------------------|
| POST   | `/mentorias`                     | Cria uma nova mentoria              |
| GET    | `/mentorias`                     | Lista todas as mentorias            |
| GET    | `/mentorias?nome={nome}`         | Filtra por nome do mentor           |
| GET    | `/mentorias?especialidade={esp}` | Filtra por especialidade            |
| GET    | `/mentorias?futuras=true`        | Lista apenas mentorias futuras      |
| GET    | `/mentorias?futuras=false`       | Lista apenas mentorias passadas     |
| GET    | `/mentorias/{id}`                | Busca mentoria por ID               |
| DELETE | `/mentorias/{id}`                | Remove uma mentoria                 |

**Corpo da requisição (POST):**
```json
{
  "titulo": "Introdução ao Java",
  "especialidade": "Backend",
  "dataHora": "2026-06-15T10:00:00",
  "mentorId": 1
}
```

---

## Regras de negócio

- E-mails de usuários devem ser únicos — um e-mail já cadastrado resultará em erro de constraint no banco de dados.
- Apenas usuários do tipo `MENTOR` podem criar mentorias — caso contrário, retorna erro `400`.
- O mentor referenciado em uma mentoria deve existir — caso contrário, retorna `404`.
- A data da mentoria deve ser uma data futura (validada pela anotação `@Future`).

---

## Tratamento de erros

| Exceção                          | Status HTTP | Situação                              |
|----------------------------------|-------------|---------------------------------------|
| `ApenasMentorException`          | 400         | Usuário não é mentor                  |
| `MentoriaNaoEncontradaException` | 404         | Mentoria não encontrada pelo ID       |
| `UsuarioNaoEncontradoException`  | 404         | Usuário/Mentor não encontrado pelo ID |
