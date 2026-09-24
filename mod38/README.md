# Cadastro web JSF

Interface JSF (Jakarta Faces 4), controller de visão, serviço de validação, repositório e JPA/Hibernate. Persistência em PostgreSQL, sem credenciais no código. O WAR roda no Tomcat 10.1 com Java 17. CRUD de clientes com mensagens explícitas de sucesso ou falha.

1. Crie um banco de exercício e execute `schema.sql` com psql.
2. Defina EBAC_DB_URL, EBAC_DB_USER e EBAC_DB_PASSWORD no ambiente do Tomcat.
3. Na raiz, execute `mvn -pl mod38 -am verify`.
4. Copie `mod38/target/cadastro-jsf.war` para `webapps` do Tomcat.
5. Abra `/cadastro-jsf/` no endereço local do servidor.

O módulo 39 contém as capturas da implantação. O formulário permite cadastrar, editar e excluir. E-mail tem restrição de unicidade no banco. As consultas e alterações executam em transações; falhas causam rollback. O estado da visão JSF protege os postbacks. Este é um ambiente didático local; autenticação e HTTPS devem ser configurados antes de exposição externa.
