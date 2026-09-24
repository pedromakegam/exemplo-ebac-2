# CRUD de Cliente e Produto com JDBC

Implementação com JDBC, PreparedStatement e fechamento de Connection, Statement e ResultSet por try-with-resources. As tabelas e as consultas ficam na camada DAO. Os testes usam PostgreSQL real em um schema isolado por execução.

Configure EBAC_DB_URL, EBAC_DB_USER e EBAC_DB_PASSWORD no ambiente antes de executar os testes. A URL deve apontar para um banco de exercícios. Exemplo de URL local: jdbc:postgresql://127.0.0.1:55439/ebac. Sem EBAC_DB_URL, os testes de integração ficam desabilitados. Não coloque senhas no repositório.

O schema de teste é criado automaticamente com prefixo teste_mod29_ e preservado para inspeção após a execução. O SQL usado está em src/main/resources/schema.sql.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod29 -am test
```
