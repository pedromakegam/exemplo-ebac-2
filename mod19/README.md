# Leitura de annotation em execução

@Tabela recebe o nome da tabela e fica disponível em runtime. A leitura usa Class.getAnnotation e trata classes sem a anotação.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod19 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -Dfile.encoding=UTF-8 -cp mod19/target/classes br.com.pedro.ebac.ExemploTabela
```
