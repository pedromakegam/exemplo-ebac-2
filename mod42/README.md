# Microsserviços de clientes e produtos

Dois processos independentes, cada um com seu próprio banco, contrato HTTP e build. A aplicação de clientes não depende das tabelas de produtos e vice-versa.

Defina DB_PASSWORD no ambiente. Em terminais separados, a partir da raiz:

```sh
SERVER_PORT=18081 mvn -pl mod42/cliente-service spring-boot:run
SERVER_PORT=18082 mvn -pl mod42/produto-service spring-boot:run
```

As rotas são /clientes e /produtos. Cada serviço suporta POST, GET, PUT e DELETE; a consulta individual usa /{id}. GET /actuator/health informa a saúde do processo. Os READMEs de cada serviço detalham o banco e os testes.
