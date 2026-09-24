# Memelândia em três serviços

Migração do monólito fornecido pela EBAC: https://github.com/github-ebac/backend-java-pro/tree/main/modulo-15-aula-2/memelandia. O projeto original usa um controller, um serviço, três repositórios e relacionamentos JPA entre os domínios. Esta versão reimplementa os mesmos cadastros com processos e bancos separados. O material-base continua sendo de autoria da EBAC.

| Antes | Depois |
| --- | --- |
| Um processo Spring Boot 2.7 / Java 11 | Três processos Spring Boot 3.5 / Java 17 |
| Entidades relacionadas no mesmo banco | IDs e consulta HTTP entre serviços |
| Usuario, CategoriaMeme e Meme | Um domínio em cada serviço |
| Meme sem URL no modelo-base | URL de imagem/vídeo validada conforme o enunciado |

## Executar

Defina DB_PASSWORD no ambiente. A partir da raiz do repositório, em terminais separados:

```sh
SERVER_PORT=18101 mvn -pl java-pro/mod17/usuarios-service spring-boot:run
SERVER_PORT=18102 USUARIOS_URL=http://127.0.0.1:18101 mvn -pl java-pro/mod17/categorias-service spring-boot:run
SERVER_PORT=18103 USUARIOS_URL=http://127.0.0.1:18101 CATEGORIAS_URL=http://127.0.0.1:18102 mvn -pl java-pro/mod17/memes-service spring-boot:run
```

Cada serviço usa arquivo H2 próprio e migração Flyway. DB_URL, DB_USER e DB_PASSWORD permitem configurar bancos PostgreSQL independentes. A configuração padrão escuta somente no computador local. Para serviços remotos, as URLs devem usar HTTPS; o cliente mantém a verificação padrão do certificado e não segue redirecionamentos.

GET e POST: /memelandia/usuarios, /memelandia/categorias e /memelandia/memes, em suas respectivas portas. GET /{id} consulta um registro. Há também GET /memelandia/memes/aleatorio. Exemplos completos e uma verificação real dos três processos estão em smoke_test.py.

## Integridade e falhas

A categoria verifica a existência do usuário. O meme verifica usuário e categoria antes de gravar. Referência ausente retorna 422; indisponibilidade retorna 503 e não grava dados. Chamadas externas têm limite de conexão de 2 segundos e total de 3 segundos. Não há exclusão de usuários/categorias neste contrato, conforme o escopo de cadastro e consulta do enunciado. Isso mantém as referências verificadas estáveis. Se exclusão for acrescentada, será necessário definir protocolo de inativação/eventos e revisar essa garantia. Não há transação distribuída nem afirmação de atomicidade entre bancos.

A URL do meme é armazenada, sem buscar o arquivo remoto no servidor. Todos os exemplos usam dados fictícios. A seleção aleatória usa paginação; um catálogo muito grande justificaria outra estratégia medida em teste de carga.

## Operação e 12 fatores

Cada domínio tem artefato, dependências e banco próprios. Portas, credenciais e endereços ficam no ambiente. A aplicação não mantém sessão de usuário no processo; o armazenamento é um serviço externo configurável. Maven separa build e execução. Os logs saem no console com método, padrão da rota, status e duração, sem corpos, e-mails ou segredos. Todos os endpoints passam pelo filtro, incluindo falhas e Actuator.

GET /actuator/health verifica a saúde. GET /actuator/metrics expõe as métricas, inclusive http.server.requests depois de um acesso HTTP real. Health não expõe detalhes do banco. Métricas estão disponíveis apenas no endereço local padrão; uma implantação externa deve restringir a rede de gerenciamento. Registro de serviços, balanceador e tracing distribuído não fazem parte desta entrega opcional.

## Testes

`mvn -pl java-pro/mod17/usuarios-service,java-pro/mod17/categorias-service,java-pro/mod17/memes-service -am verify`

Os testes verificam validação, persistência, respostas 404/422/503, referências, ausência de gravação em falhas, seleção aleatória, métricas e o cliente HTTP com um servidor de teste real. Não há migração automática dos registros existentes do monólito: a entrega usa bancos novos de demonstração. Para migrar dados reais, é necessário preservar IDs e importar usuários, categorias e memes nessa ordem, com conferência posterior.
