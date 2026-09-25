# Validação — módulo 30

Execução local em 24/09/2026 com JDK 17.0.17, Maven 3.9.16, driver PostgreSQL 42.7.13 e PostgreSQL 16.11. Foi utilizado PostgreSQL real em uma instância local destinada aos testes.

Comando, com as variáveis de conexão já configuradas:

```bash
mvn -pl mod30 clean verify
```

| Classe | Testes | Falhas | Erros | Ignorados |
|---|---:|---:|---:|---:|
| ClienteDAOTest | 5 | 0 | 0 | 0 |
| ProdutoDAOTest | 5 | 0 | 0 | 0 |
| VendaDAOTest | 11 | 0 | 0 | 0 |
| ClienteServiceTest | 4 | 0 | 0 | 0 |
| ProdutoServiceTest | 4 | 0 | 0 | 0 |
| NovosCamposDAOTest | 10 | 0 | 0 | 0 |
| **Total** | **39** | **0** | **0** | **0** |

Trecho do resultado do Maven:

```text
[INFO] Tests run: 39, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

Os testes dos serviços usam os mocks do material da aula. Os testes de DAO e dos novos campos usam conexão JDBC com PostgreSQL. Cada classe de integração cria e remove seu próprio schema. A validação inclui a migração aplicada duas vezes sobre cadastros existentes e o rollback de uma venda com referência a produto inexistente.
