# Verificação das entregas

Execuções em 24/09/2026 com JDK 17, Maven 3.9.16 e PostgreSQL 16.11 local.

Os módulos foram compilados e testados em lotes pelo Maven. Os testes JDBC/JPA usaram um banco de exercícios real, com schemas isolados. A geração de JavaDoc da calculadora terminou sem avisos.

| Módulo | Testes | Falhas | Erros | Ignorados |
|---|---:|---:|---:|---:|
| java-pro/mod1 | 4 | 0 | 0 | 0 |
| java-pro/mod2 | 4 | 0 | 0 | 0 |
| java-pro/mod3 | 4 | 0 | 0 | 0 |
| java-pro/mod5 | 11 | 0 | 0 | 0 |
| mod13 | 2 | 0 | 0 | 0 |
| mod14 | 3 | 0 | 0 | 0 |
| mod15 | 2 | 0 | 0 | 0 |
| mod17 | 1 | 0 | 0 | 0 |
| mod18 | 2 | 0 | 0 | 0 |
| mod19 | 2 | 0 | 0 | 0 |
| mod22 | 4 | 0 | 0 | 0 |
| mod23 | 4 | 0 | 0 | 0 |
| mod24 | 10 | 0 | 0 | 0 |
| mod25 | 32 | 0 | 0 | 0 |
| mod29 | 5 | 0 | 0 | 0 |
| mod30 | 8 | 0 | 0 | 0 |
| mod32 | 2 | 0 | 0 | 0 |
| mod33 | 1 | 0 | 0 | 0 |
| mod35 | 3 | 0 | 0 | 0 |
| mod36 | 6 | 0 | 0 | 0 |
| mod37 | 2 | 0 | 0 | 0 |

Total: 112 testes. A classe main do módulo 5 também foi executada e imprimiu Hello Pedro!.

No módulo 24, os dez testes falharam antes da implementação e passaram depois. Os scripts SQL dos módulos 27 e 28 foram executados com ON_ERROR_STOP e suas saídas foram preservadas em execucao.txt.
