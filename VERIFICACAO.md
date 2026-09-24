# Verificação das entregas

Execuções em 24/09/2026 com JDK 17, Maven 3.9.16 e PostgreSQL 16.11 local.

Os módulos foram compilados e testados em lotes pelo Maven. JDBC/JPA usaram PostgreSQL real e schemas isolados. Spring Boot foi testado com contexto completo e H2 de teste. JavaDoc da calculadora terminou sem avisos.

| Módulo | Testes | Falhas | Erros | Ignorados |
|---|---:|---:|---:|---:|
| java-pro/mod1 | 4 | 0 | 0 | 0 |
| java-pro/mod11 | 7 | 0 | 0 | 0 |
| java-pro/mod17/categorias-service | 9 | 0 | 0 | 0 |
| java-pro/mod17/memes-service | 13 | 0 | 0 | 0 |
| java-pro/mod17/usuarios-service | 4 | 0 | 0 | 0 |
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
| mod38 | 3 | 0 | 0 | 0 |
| mod40 | 3 | 0 | 0 | 0 |
| mod42/cliente-service | 3 | 0 | 0 | 0 |
| mod42/produto-service | 3 | 0 | 0 | 0 |

Total: 157 testes, sem falhas, erros ou ignorados.

A main do módulo 5 imprimiu Hello Pedro!. No módulo 24, dez testes falharam antes da implementação e passaram depois. SQL 27/28 foi executado com ON_ERROR_STOP; saídas e capturas estão nas pastas.

O cadastro JSF foi implantado no Tomcat 10.1.60. Cadastro, alteração e exclusão foram executados no navegador com PostgreSQL, conforme as capturas do módulo 39.

As APIs 40/42 foram executadas por HTTP real: saudação, saúde, cadastro, consulta, alteração e exclusão. Os três processos Memelândia foram executados juntos; o fluxo de cadastro, referências inexistentes, falha de dependência, métricas e logs foram verificados por smoke_test.py. O relatório está em java-pro/mod17/verificacao-http.txt.

O encurtador de URLs é uma proposta de arquitetura e não foi submetido a teste de carga. Nenhum serviço foi publicado na internet.
