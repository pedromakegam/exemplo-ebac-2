# Exercícios de Back-End Java

Entregas organizadas por módulo. Os exercícios de Java Pro ficam em `java-pro/`. Cada projeto contém seu enunciado resumido, código e instruções de execução. As respostas teóricas estão em Markdown e PDF.

## Executar

Requer JDK 17 e Maven.

```sh
mvn test
```

Para testar um módulo: `mvn -pl mod22 -am test`. Os resultados da execução estão em [VERIFICACAO.md](VERIFICACAO.md).

## Conteúdo

- [java-pro/mod1](./java-pro/mod1)
- [java-pro/mod2](./java-pro/mod2)
- [java-pro/mod3](./java-pro/mod3)
- [java-pro/mod5](./java-pro/mod5)
- [mod13](./mod13)
- [mod15](./mod15)
- [mod17](./mod17)
- [mod18](./mod18)
- [mod19](./mod19)
- [mod22](./mod22)
- [mod23](./mod23)
- [mod5](./mod5)
- [mod6](./mod6)

Os dados usados nos exemplos e testes são fictícios.

## Testes com PostgreSQL

Os módulos 29, 30, 32, 33, 35 e 36 precisam de EBAC_DB_URL, EBAC_DB_USER e EBAC_DB_PASSWORD. Configure as variáveis para um banco de exercícios antes de rodar Maven. Sem EBAC_DB_URL, os testes de integração ficam desabilitados. Todos esses testes foram executados com PostgreSQL real na validação registrada.


## Entregas finais

JSF e implantação: mod38 e mod39. Spring Boot e APIs independentes: mod40 e mod42. Abrigo: java-pro/mod11. Desenho do encurtador: java-pro/mod16. Memelândia com três serviços: java-pro/mod17. Resultados de execução: VERIFICACAO.md.
