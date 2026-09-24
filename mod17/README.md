# Garagem genérica

Garagem<T extends Carro> aceita carros do tipo escolhido. O exemplo usa Garagem<Carro> com modelos de duas subclasses.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod17 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -Dfile.encoding=UTF-8 -cp mod17/target/classes br.com.pedro.ebac.ExemploGaragem
```
