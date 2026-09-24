# Projeto 4 - vendas com JPA

Evolução do modelo JDBC do módulo 30 para entidades JPA e DAOs genéricos. Cliente conserva email e Produto conserva categoria. Venda persiste itens por cascade e guarda o preço unitário histórico. A transação engloba toda a venda.

JPA com Hibernate e PostgreSQL. Configure EBAC_DB_URL, EBAC_DB_USER e EBAC_DB_PASSWORD para executar os testes de integração. Eles ficam desabilitados sem a URL. Cada execução cria um schema com nome aleatório e conserva os dados para inspeção; não usa tabelas existentes.

A geração automática de tabelas está restrita ao método de preparação do banco de testes. Em uma aplicação implantada, use migrações e valide o esquema.

Referências: https://hibernate.org/orm/releases/6.6/ e https://jdbc.postgresql.org/download/

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod35 -am test
```
