# API Registro Meteorológico

Primeiramente, inicie o banco MySQL localizado na pasta `compose` no terminal:

```bash
cd compose
docker compose up -d
```

## Executando a aplicação

Via IntelliJ, Run 'ApiRegistroAnna557473M1Application':

OU execute pelo terminal:

### Profile Test (H2)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=test
```

### Profile Dev (MySQL)
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```

---

## Executando a imagem Docker da API

Gerar a imagem:

```bash
docker build -t api-registro-anna .
```

Executar o container:

```bash
docker run -p 8080:8080 api-registro-anna
```

---

## Acessando a aplicação

Disponível no profile `test`:

### H2 Console

```text
http://localhost:8080/h2-console/
```

### Swagger

Documentação da API:

```text
http://localhost:8080/swagger-ui/index.html
```
