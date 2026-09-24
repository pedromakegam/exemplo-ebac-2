# Abrigo de animais

Cadastro de funcionários, raças e animais (cães e gatos). Relatório de resgates por funcionário com intervalo máximo de um ano. Implementação do enunciado do módulo, com modelo explícito no código; não foi disponibilizado um projeto-base de abrigo junto ao exercício.

JDK 17 e Maven. Na raiz do repositório:

```sh
mvn -pl java-pro/mod11 -am verify
mvn -pl java-pro/mod11 spring-boot:run
```

A aplicação escuta apenas em 127.0.0.1 por padrão. A porta pode ser definida por SERVER_PORT. Os testes sobem o contexto Spring e exercitam os endpoints com MockMvc.

O perfil padrão usa H2 em arquivo no diretório .data, ignorado pelo Git. Defina DB_PASSWORD no ambiente antes de iniciar. DB_URL e DB_USER permitem escolher outro banco (H2 ou PostgreSQL); cada serviço precisa do seu próprio banco. Flyway cria o esquema e Hibernate apenas o valida. Os testes usam H2 em memória com dados fictícios e uma senha exclusiva de teste.

## Contrato

POST/GET /funcionarios, /racas e /animais. Uma raça informa especie CACHORRO ou GATO; o animal recebe racaId, funcionarioId e dataResgate. Assim, o cadastro não mantém campos duplicados de espécie que possam se contradizer.

GET /relatorios/resgates?inicio=2024-01-01&fim=2025-01-01 considera início inclusivo e fim exclusivo, incluindo todo o ano de 2024. O limite é um ano de calendário, inclusive anos bissextos. Funcionários sem resgates aparecem com zero. Intervalos vazios, invertidos ou maiores que um ano retornam 400. Referências inexistentes retornam 404 e não gravam um animal.
