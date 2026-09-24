# Projeto 2 - vendas em memória

Projeto das aulas de Rodrigo Pires, com Cliente, Produto, Venda, DAO genérico e serviços. A chave de cada entidade é encontrada pela annotation TipoChave.

Base: https://github.com/digaomilleniun/backend-java-ebac/tree/main/mod25/ExemploVendasMod25

Adaptações: configuração Maven com JUnit 4/Vintage, retirada da suíte agregadora para não repetir testes e validação das quantidades e do estado da venda. Os autores dos arquivos originais foram preservados.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod25 -am test
```
