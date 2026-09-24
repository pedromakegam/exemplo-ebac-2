# Filtro de pessoas com streams

Entrada no formato Ana-F, Bruno-M, Carla-F. O parser valida nome e gênero (F/M ou feminino/masculino). A lista de mulheres é uma nova lista produzida por stream, sem alterar a entrada.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod22 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -cp mod22/target/classes br.com.pedro.ebac.FiltroPessoas
```
