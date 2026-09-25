# Projeto 3 — módulo 30

Correção feita a partir do **ExemploVendasMod30**, disponibilizado nas aulas. Foram mantidos os pacotes `br.com.rpires` e `anotacao`, as interfaces, o `GenericDAO`, os serviços, as factories e os testes do projeto original.

Base: [backend-java-ebac/mod30/ExemploVendasMod30](https://github.com/digaomilleniun/backend-java-ebac/tree/a841df3ca36f5ba0878c6ca0338e63ccc072b815/mod30/ExemploVendasMod30), de Rodrigo Pires. Os comentários de autoria do material foram preservados. O Maven foi acrescentado para facilitar a execução fora do STS.

## Campos acrescentados

| Entidade | Campo Java | Coluna no PostgreSQL |
|---|---|---|
| Cliente | `String email` | `tb_cliente.email varchar(150)` |
| Produto | `String categoria` | `tb_produto.categoria varchar(60)` |

Os campos têm getters, setters e `@ColunaTabela`. Os `INSERT` e `UPDATE` de `ClienteDAO` e `ProdutoDAO` receberam as colunas e os parâmetros correspondentes. O `GenericDAO` já lê `String` por reflexão; por isso, as consultas por chave e a listagem reconhecem os campos pela anotação.

As consultas de `VendaDAO` também retornam `email` e `categoria`, e `ClienteFactory` e `ProdutoFactory` preenchem esses valores ao montar uma venda.

Arquivos principais:

- [Cliente.java](src/main/java/br/com/rpires/domain/Cliente.java) e [ClienteDAO.java](src/main/java/br/com/rpires/dao/ClienteDAO.java).
- [Produto.java](src/main/java/br/com/rpires/domain/Produto.java) e [ProdutoDAO.java](src/main/java/br/com/rpires/dao/ProdutoDAO.java).
- [VendaDAO.java](src/main/java/br/com/rpires/dao/VendaDAO.java) e [factories](src/main/java/br/com/rpires/dao/factory).
- [Migração das duas colunas](src/main/resources/migracao-mod30.sql).
- [Testes dos novos campos](src/test/java/br/com/rpires/NovosCamposDAOTest.java).

## Banco de dados

Para um banco novo, execute primeiro [schema-base.sql](src/main/resources/schema-base.sql), extraído do SQL da aula, e depois [migracao-mod30.sql](src/main/resources/migracao-mod30.sql). Se as tabelas da aula já existem, execute somente a migração no mesmo schema.

A migração usa `ADD COLUMN IF NOT EXISTS` e mantém os registros antigos. As novas colunas aceitam `NULL` para preservar os cadastros anteriores e os cenários de teste da aula.

A conexão usa as variáveis `EBAC_DB_URL`, `EBAC_DB_USER` e `EBAC_DB_PASSWORD`. Não há senha no código. `EBAC_DB_SCHEMA` é opcional para escolher um schema existente. Exemplo de URL para um banco local: `jdbc:postgresql://127.0.0.1:5432/vendas_online_2`.

## Execução dos testes

Requisitos: JDK 17, Maven e PostgreSQL. Configure as três variáveis de conexão para **um banco de desenvolvimento ou testes**, com permissão de criar schemas, e execute a partir da raiz do repositório:

```bash
mvn -pl mod30 clean verify
```

Os testes de banco criam um schema exclusivo por classe e o removem ao terminar. Não limpam tabelas do schema informado pelo usuário. Sem as variáveis de conexão, os testes de banco falham com uma mensagem de configuração, em vez de serem ignorados.

Os 29 testes da aula foram mantidos. Nos testes de DAO, foi acrescentada a preparação do banco isolado; também foi corrigido o fechamento da conexão na rotina de limpeza de `VendaDAOTest`. O Maven executa cada classe uma vez, excluindo a suíte agregadora `AllTests` para não duplicar a contagem.

Os 10 testes adicionais verificam:

- Gravação e consulta direta no PostgreSQL dos dois novos campos.
- Alteração de cada campo preservando os demais dados.
- Leitura pela listagem genérica e pelas associações de vendas.
- Listagem de duas vendas na mesma consulta.
- Migração repetida sem perda dos cadastros anteriores.
- Rollback da venda quando um item não pode ser gravado.

Ao integrar os campos à consulta de vendas, foram ajustados os fechamentos das conexões para que a leitura dos itens não interrompa a listagem. O cadastro da venda e de seus itens passou a usar uma única transação. A validação de registros do `GenericDAO` agora propaga falhas de SQL.

Resultado da execução: **39 testes, nenhuma falha, erro ou teste ignorado**. Detalhes em [VALIDACAO.md](VALIDACAO.md).
