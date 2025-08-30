# Turmas API (Spring Boot 3)

API REST para gerenciar **Turmas**, seguindo arquitetura em camadas (Controller, Service, Repository), DTOs e validações.

## Como rodar (H2 - padrão)
```bash
mvn spring-boot:run
# Console H2: http://localhost:8080/h2-console  (JDBC URL: jdbc:h2:mem:turmasdb)
```

## Endpoints

### Criar turma
`POST /turmas` → **201 Created**  
Body (JSON):
```json
{ "nome":"Direito A", "periodo":"Manhã", "curso":"Direito" }
```
Erros: 400 se faltar campos obrigatórios.

### Buscar por id
`GET /turmas/{id}` → **200 OK** (ou **404 Not Found**)

### Buscar todas
`GET /turmas` → **200 OK** com lista, ou **204 No Content** se vazio

### Buscar por nome
`GET /turmas/nome/{nome}` → **200 OK** (ou **404 Not Found**)

### Buscar por período
`GET /turmas/periodo/{periodo}` → **200 OK** (ou **404 Not Found**)

### Buscar por curso
`GET /turmas/curso/{curso}` → **200 OK** (ou **404 Not Found**)

### Deletar por id
`DELETE /turmas/{id}` → **204 No Content** (ou **404 Not Found**)

## Desafio: Banco real (Postgres/MySQL)
- Altere `src/main/resources/application.properties` conforme seu banco e adicione o driver no `pom.xml` (comentado no pom).
- As tabelas são criadas automaticamente via `spring.jpa.hibernate.ddl-auto=update`.

## Camadas
- `controller`: endpoints e códigos HTTP corretos.
- `service`: regras de negócio, validações e exceções customizadas.
- `repository`: acesso a dados (Spring Data JPA).
- `dto`: contrato de entrada/saída.
- `entity`: mapeamento JPA.

## Testes rápidos (curl)
```bash
curl -X POST http://localhost:8080/turmas -H "Content-Type: application/json" -d '{"nome":"ADS 1","periodo":"Noite","curso":"ADS"}'
curl -i http://localhost:8080/turmas
curl -i http://localhost:8080/turmas/1
curl -i http://localhost:8080/turmas/nome/ADS%201
curl -i http://localhost:8080/turmas/periodo/Noite
curl -i http://localhost:8080/turmas/curso/ADS
curl -i -X DELETE http://localhost:8080/turmas/1
```
