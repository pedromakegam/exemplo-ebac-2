# Entidade Produto em JPA

Produto mapeia código, nome e preço, além do identificador gerado pelo banco. Categoria complementa o cadastro. O teste consulta a entidade em outro EntityManager e confirma a existência da linha na tabela criada.

JPA com Hibernate e PostgreSQL. Configure EBAC_DB_URL, EBAC_DB_USER e EBAC_DB_PASSWORD para executar os testes de integração. Eles ficam desabilitados sem a URL. Cada execução cria um schema com nome aleatório e conserva os dados para inspeção; não usa tabelas existentes.

A geração automática de tabelas está restrita ao método de preparação do banco de testes. Em uma aplicação implantada, use migrações e valide o esquema.

Referências: https://hibernate.org/orm/releases/6.6/ e https://jdbc.postgresql.org/download/

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod32 -am test
```
