# Primeiro projeto Spring Boot

API de saudação com parâmetro nome e endpoint de saúde. Exemplo: GET /saudacao?nome=Pedro. O nome vazio é rejeitado. O teste verifica também /actuator/health.

JDK 17 e Maven. Na raiz do repositório:

```sh
mvn -pl mod40 -am verify
mvn -pl mod40 spring-boot:run
```

A aplicação escuta apenas em 127.0.0.1 por padrão. A porta pode ser definida por SERVER_PORT. Os testes sobem o contexto Spring e exercitam os endpoints com MockMvc.
