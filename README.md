# Banco Horizonte API

API de agência bancária sem Spring, construída com Java 25, Maven, JPA/Hibernate, PostgreSQL e `HttpServer` do JDK.

## Requisitos

- JDK 25
- Maven 3.9 ou superior
- PostgreSQL 14 ou superior

## Banco de dados

```sql
CREATE DATABASE banco_horizonte;
```

```bash
psql -U postgres -d banco_horizonte -f src/main/resources/db/init.sql
```

## Execução

```bash
mvn clean test
mvn exec:java
```

A API inicia em `http://localhost:8080`.

Consulte `requests.http` para as requisições de teste.