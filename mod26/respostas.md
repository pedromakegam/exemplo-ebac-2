# PostgreSQL

## Para que serve

PostgreSQL é um sistema de gerenciamento de banco de dados objeto-relacional. Ele armazena os dados, executa consultas SQL e controla o acesso concorrente. Em um cadastro de vendas, por exemplo, clientes, produtos e pedidos podem ficar em tabelas relacionadas por chaves.

## Recursos importantes

Chaves primárias identificam registros; chaves estrangeiras protegem relacionamentos; constraints como NOT NULL e CHECK ajudam a impedir valores inválidos. Transações permitem confirmar um conjunto de alterações ou desfazê-lo quando algo falha. Isso é importante quando uma venda precisa gravar seus itens e atualizar o estoque como uma única operação.

## Cuidados de uso

Índices podem acelerar consultas, mas também ocupam espaço e aumentam o trabalho nas escritas. A escolha depende das consultas reais. O banco também precisa de migrações, backups testados e usuários com permissões adequadas. Ter um banco robusto não substitui essas práticas.

## Referência

PostgreSQL - What Is PostgreSQL? https://www.postgresql.org/docs/current/intro-whatis.html
