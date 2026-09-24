# Verificação das entregas

Execuções em 24/09/2026 com JDK 17 e Maven 3.9.16.

Primeiro lote: `mvn test`. Segundo lote: `mvn -pl mod14,mod24,mod25 -am test`. Ambos terminaram com BUILD SUCCESS.

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

Total: 85 testes. A classe main do módulo 5 também foi executada e imprimiu Hello Pedro!.

No módulo 24, a primeira execução, antes de implementar os métodos, falhou nos dez testes. Depois da implementação, os dez testes passaram.
