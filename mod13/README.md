# Pessoas físicas e jurídicas

Pessoa concentra nome e endereço. As subclasses acrescentam CPF/data de nascimento ou CNPJ/razão social. Os documentos usados na demonstração são fictícios.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod13 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -cp mod13/target/classes br.com.pedro.ebac.ExemploPessoas
```
