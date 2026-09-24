# Memelândia - memes

Serviço separado a partir do monólito oficial. Cadastro e consulta em /memelandia/memes.

JDK 17 e Maven. Na raiz do repositório:

```sh
mvn -pl java-pro/mod17/memes-service -am verify
mvn -pl java-pro/mod17/memes-service spring-boot:run
```

A aplicação escuta apenas em 127.0.0.1 por padrão. A porta pode ser definida por SERVER_PORT. Os testes sobem o contexto Spring e exercitam os endpoints com MockMvc.

O perfil padrão usa H2 em arquivo no diretório .data, ignorado pelo Git. Defina DB_PASSWORD no ambiente antes de iniciar. DB_URL e DB_USER permitem escolher outro banco (H2 ou PostgreSQL); cada serviço precisa do seu próprio banco. Flyway cria o esquema e Hibernate apenas o valida. Os testes usam H2 em memória com dados fictícios e uma senha exclusiva de teste.
