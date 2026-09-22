# Banco Horizonte API

Desafio de agência bancária implementado com Java 25, Spring Boot, Spring Data JPA e PostgreSQL.

## Requisitos

- JDK 25
- Maven 3.9+
- PostgreSQL 14+

## Banco de dados

```sql
CREATE DATABASE banco_horizonte;
```

A aplicação cria as tabelas e carrega os tipos `CORRENTE`, `POUPANCA` e `SALARIO` por `schema.sql` e `data.sql`.

## Executar

```bash
mvn clean test
mvn spring-boot:run
```

A API inicia em `http://localhost:8080`. As seis rotas obrigatórias e exemplos de uso estão em [requests.http](requests.http).

## Regras garantidas

- CPF e agência+número únicos;
- conta vinculada a pessoa e tipo existentes;
- valores monetários com `BigDecimal`;
- conta inativa, valor inválido e saldo insuficiente retornam conflito;
- operações de escrita são transacionais.
