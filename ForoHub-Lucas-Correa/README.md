# ForoHub - Lucas Correa

API REST de foro (Java 17 + Spring Boot 3 + JWT + H2). Proyecto base para el Challenge Alura ForoHub.

## Endpoints

- `POST /auth/register` — registra un usuario (email & password)
- `POST /auth/login` — retorna token **Bearer**
- `GET /topicos` — lista tópicos (requiere token)
- `POST /topicos` — crea tópico (requiere token)
- `DELETE /topicos/{id}` — elimina tópico (requiere token)

## Cómo correr

```bash
./mvnw spring-boot:run
# o con Maven instalado
mvn spring-boot:run
```

Usuario admin por defecto: `admin@forohub.com` / `123456`

## Autor

**Lucas Correa**
