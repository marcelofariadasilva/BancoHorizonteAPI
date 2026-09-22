# Banco Horizonte API

API de agência bancária sem Spring, construída com Java 25, Maven, JPA/Hibernate, PostgreSQL e `HttpServer` do JDK.

## Requisitos

- JDK 25
- Maven 3.9 ou superior
- PostgreSQL 14 ou superior

## Banco de dados

Crie a base e execute o script de carga:

```sql
CREATE DATABASE banco_horizonte;
```

```bash
psql -U postgres -d banco_horizonte -f src/main/resources/db/init.sql
```

O `persistence.xml` usa `localhost:5432`, usuário `postgres` e senha `postgres`. Ajuste esses valores se o seu PostgreSQL usar outra configuração.

## Abrir no IntelliJ

1. Descompacte o projeto.
2. No IntelliJ, selecione **File > Open** e escolha a pasta `banco-horizonte-api`.
3. Confirme a importação como projeto Maven e selecione o JDK 25.
4. Execute `br.com.bancohorizonte.Application`.

A API inicia em `http://localhost:8080`.

## Executar pelo terminal

```bash
mvn clean test
mvn exec:java
```

## Endpoints

| Método | Rota | Resultado |
|---|---|---|
| POST | `/api/pessoas` | Cadastra pessoa |
| POST | `/api/contas` | Abre conta |
| GET | `/api/contas/{id}` | Consulta uma conta com titular e tipo |
| GET | `/api/contas/pessoa/{pessoaId}` | Lista contas do titular |
| PATCH | `/api/contas/{id}/depositos` | Efetua depósito |
| PATCH | `/api/contas/{id}/saques` | Efetua saque |

## Exemplos cURL

```bash
curl -X POST http://localhost:8080/api/pessoas -H "Content-Type: application/json" -d '{"nome":"Camila Ferreira","cpf":"55566677788","email":"camila@example.com"}'
curl -X POST http://localhost:8080/api/contas -H "Content-Type: application/json" -d '{"agencia":"0001","numero":"200001-9","saldoInicial":250.00,"ativa":true,"titularId":1,"tipoContaId":1}'
curl http://localhost:8080/api/contas/1
curl -X PATCH http://localhost:8080/api/contas/1/depositos -H "Content-Type: application/json" -d '{"valor":100.00}'
curl -X PATCH http://localhost:8080/api/contas/1/saques -H "Content-Type: application/json" -d '{"valor":75.50}'
```

Também há requisições prontas em `requests.http`, executáveis diretamente pelo IntelliJ com o plugin HTTP Client.

## Regras implementadas

- CPF com 11 dígitos e unicidade.
- E-mail validado e nome obrigatório.
- Agência com quatro dígitos; conta no padrão `123456-7`.
- Saldo inicial não negativo; depósitos e saques somente positivos.
- Conta inativa, saldo insuficiente e duplicidades retornam HTTP 409.
- Objetos JPA não são expostos pela API: todas as respostas usam DTOs.
- Operações de escrita usam `EntityTransaction` com `begin`, `commit` e `rollback`.
