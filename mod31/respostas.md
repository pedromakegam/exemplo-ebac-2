# JPA e mapeamento de entidades

## O papel da JPA

JPA é uma especificação para persistir objetos Java em um banco relacional. Ela define contratos e anotações; quem executa o trabalho é uma implementação, como Hibernate. Uma classe marcada com @Entity pode representar uma tabela, e seu campo @Id representa a identidade do registro.

## Como o mapeamento ajuda

Em um Produto, os atributos nome e preco podem ser mapeados para colunas. Relacionamentos, como os itens de uma venda, também podem ser descritos no modelo. O EntityManager controla o contexto de persistência e operações como persist, find e remove. Alterações em entidades gerenciadas podem ser sincronizadas com o banco dentro da transação.

## Limites

O mapeamento reduz código repetitivo, mas não elimina SQL nem a necessidade de entender o banco. É preciso observar transações, carregamento de relacionamentos e a quantidade de consultas. Um relacionamento mal usado pode causar várias consultas extras. A geração automática de tabelas é útil no exercício; em um sistema em uso, as alterações devem ser feitas por migrações controladas.

## Referência

Jakarta Persistence - especificação 3.1. https://jakarta.ee/specifications/persistence/3.1/jakarta-persistence-spec-3.1
