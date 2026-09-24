# Projeto Maven com dependência externa

Gson converte um objeto Produto em JSON e faz a leitura de volta. O pom declara Gson para a aplicação e JUnit para os testes. Maven resolve as dependências e organiza compilação e testes.

Execute `mvn -pl mod37 -am test`. A versão da biblioteca está fixada no pom para manter o resultado reproduzível. Referência: https://github.com/google/gson

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod37 -am test
```
