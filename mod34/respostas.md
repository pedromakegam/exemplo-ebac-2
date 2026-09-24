# Redis e Cassandra

## Redis

Redis trabalha com estruturas de dados em memória, como strings, hashes, listas e conjuntos. É útil quando a aplicação precisa consultar ou alterar dados com baixa latência. Um exemplo é guardar temporariamente o resultado de uma consulta frequente, usando um prazo de expiração. Quando o dado expira, a aplicação consulta a fonte principal e preenche o cache novamente. A política de invalidação importa: um cache rápido que serve dados errados continua sendo um problema.

## Cassandra

Cassandra é um banco distribuído orientado a colunas largas. Os dados são particionados e replicados entre nós, permitindo distribuir carga e tolerar falhas. A modelagem parte das consultas que o sistema precisa atender. Por isso, não é adequado tentar reproduzir diretamente um modelo relacional cheio de joins. A chave de partição deve distribuir os dados sem concentrar todo o acesso em poucos nós.

## Quando cada um faz sentido

Usaria Redis como cache de uma consulta de catálogo ou para contadores temporários. Consideraria Cassandra para grande volume de escritas distribuídas, como eventos, quando o acesso aos dados puder ser previsto na modelagem. Não escolheria Cassandra apenas por ser NoSQL; a operação de um banco distribuído e suas regras de consistência precisam ser consideradas.

## Referências

Redis - Data structure store. https://redis.io/docs/latest/develop/get-started/data-store/
Apache Cassandra - Architecture overview. https://cassandra.apache.org/doc/latest/cassandra/architecture/overview.html
