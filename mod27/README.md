# DDL e DML de Produto

Execute em um banco de exercícios vazio, com PostgreSQL 16 ou superior:

```sh
psql -v ON_ERROR_STOP=1 -a -f mod27/produtos.sql
```

O script cria o schema mod27, cria e altera a tabela Produto, insere registros, atualiza o preço, exclui um registro de teste e consulta o resultado. ProdutoRascunho demonstra DROP TABLE sem apagar a tabela principal. Todos os dados são fictícios. As tabelas são criadas uma única vez; para repetir, use outro banco de exercícios vazio.
