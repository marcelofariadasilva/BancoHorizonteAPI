# Banco Horizonte API

Desafio de agência bancária implementado com Java 25, Spring Boot, Spring Data JPA e PostgreSQL.

## Requisitos

- JDK 25
- Spring
- Maven 3.9+
- PostgreSQL 14+

## Banco de dados

```sql
CREATE DATABASE banco_horizonte;
```

A aplicação cria as tabelas e carrega os tipos `CORRENTE`, `POUPANCA` e `SALARIO` por `schema.sql` e `data.sql`.


## Onde adicionar a URL de conexão

O ponto de configuração do projeto é o arquivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/banco_horizonte}
spring.datasource.username=${DB_USER:postgres}
spring.datasource.password=${DB_PASSWORD:}
```

Você pode editar essas três propriedades para um laboratório local. Para não salvar senha no projeto, a opção recomendada é manter o arquivo como está e configurar as variáveis de ambiente abaixo.

URL JDBC local:

```text
jdbc:postgresql://localhost:5432/banco_horizonte
```

URL JDBC do Supabase: no Dashboard do projeto, clique em **Connect**, escolha **Session pooler** e copie host, porta e usuário. Para esta API Spring Boot, que é uma aplicação persistente, use Session pooler (`5432`) ou conexão direta quando sua rede suportar IPv6. Conexões Transaction pooler (`6543`) não são indicadas para esse caso porque não suportam prepared statements. Consulte a documentação oficial do [Supabase sobre conexões](https://supabase.com/docs/guides/database/connecting-to-postgres).

Converta os dados copiados para este formato:

```text
jdbc:postgresql://HOST:PORT/DATABASE?sslmode=require
```

Exemplo de variáveis para uma conexão remota:

macOS/Linux:

```bash
export DB_URL='jdbc:postgresql://aws-0-ca-central-1.pooler.supabase.com:6543/postgres?sslmode=require'
export DB_USER='postgres.ihnullwzodkyclyslypb'
export DB_PASSWORD='Legonardo123'
```



Windows PowerShell:

```powershell
$env:DB_URL = 'jdbc:postgresql://aws-0-ca-central-1.pooler.supabase.com:6543/sslmode=require'
$env:DB_USER = 'postgres.ihnullwzodkyclyslypb'
$env:DB_PASSWORD = 'Legonardo123'
```


## Executar

Na raiz do projeto:

```bash
mvn clean test
mvn spring-boot:run
```

A API inicia em `http://localhost:8080`. As seis rotas obrigatórias e exemplos de uso estão em [requests.http](requests.http).


## Testar

Swagger: <http://localhost:8080/swagger-ui.html>



## Regras garantidas

- CPF e agência+número únicos;
- conta vinculada a pessoa e tipo existentes;
- valores monetários com `BigDecimal`;
- conta inativa, valor inválido e saldo insuficiente retornam conflito;
- operações de escrita são transacionais.