# Annotation Tabela

@Tabela recebe o nome da tabela e fica disponível em runtime. A leitura usa Class.getAnnotation e trata classes sem a anotação.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod18 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -cp mod18/target/classes br.com.pedro.ebac.ExemploTabela
```
