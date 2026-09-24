# Consistência, eventos e observabilidade

## 1. Consistência forte e eventual

Com consistência forte, uma leitura observa o resultado de uma escrita confirmada conforme as garantias do sistema, sem depender de esperar a propagação entre réplicas. Com consistência eventual, diferentes réplicas podem apresentar valores distintos por um período, mas tendem a convergir quando não há novas alterações. Eu exigiria uma garantia forte na decisão que reserva a última unidade de um produto, para evitar duas vendas da mesma unidade. Já a atualização de um painel de estatísticas pode aceitar atraso. A escolha deve ser feita para cada operação, considerando também latência e disponibilidade.

## 2. Eventos de dados e de aplicação

Um evento de dados descreve uma mudança no armazenamento, como uma linha inserida ou atualizada. É comum em captura de alterações do banco e pode refletir detalhes das tabelas. Um evento de aplicação descreve algo relevante para o negócio, como PedidoConfirmado, independentemente de quantas tabelas foram alteradas. O segundo costuma oferecer um contrato mais claro para outros serviços. Nos dois casos, o consumidor deve considerar duplicidade, ordenação e repetição. Uma fila não torna automaticamente o processamento idempotente.

## 3. Métricas que acompanharia

Começaria por volume de requisições, taxa de erros, latência e saturação dos recursos. O volume ajuda a entender a carga; os erros mostram perda de funcionalidade; percentis como p95 e p99 expõem lentidão que a média esconde; saturação indica limites de CPU, memória, conexões ou filas. Para processamento assíncrono, acompanharia tamanho da fila e idade da mensagem mais antiga. Também incluiria uma métrica de negócio, como pedidos confirmados, para perceber falhas que ainda retornem HTTP 200. Logs e traces completam a investigação, mas não substituem essas métricas.

## Referências

Google SRE - Monitoring Distributed Systems. https://sre.google/sre-book/monitoring-distributed-systems/
Martin Fowler - What do you mean by Event-Driven? https://martinfowler.com/articles/201701-event-driven.html
