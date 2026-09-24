# Monólitos e microsserviços

## Escalabilidade

Monólito - vantagens: o escalonamento inicial é simples; não exige distribuir chamadas internas pela rede. Desvantagens: é preciso replicar a aplicação inteira; partes pouco usadas também consomem recursos nas réplicas.

Microsserviços - vantagens: cada domínio pode ganhar réplicas conforme sua carga; serviços podem adotar tamanhos de máquina diferentes. Desvantagens: aumenta a quantidade de componentes a operar; um banco compartilhado ou serviço central ainda pode virar gargalo.

## Disponibilidade

Monólito - vantagens: há menos dependências de rede no fluxo; implantar réplicas idênticas é relativamente direto. Desvantagens: uma falha de processo pode afetar todos os módulos; uma atualização envolve a unidade inteira.

Microsserviços - vantagens: falhas podem ser isoladas por domínio; um serviço pode ser atualizado sem parar os demais. Desvantagens: falhas parciais são mais frequentes; sem timeouts e isolamento, um serviço lento pode derrubar vários outros.

## Consistência

Monólito - vantagens: uma transação local pode abranger várias operações; regras envolvendo os mesmos dados são mais fáceis de coordenar. Desvantagens: o compartilhamento das tabelas pode acoplar módulos; transações longas podem aumentar contenção e bloqueios.

Microsserviços - vantagens: cada domínio define suas próprias invariantes; a propriedade dos dados fica mais clara. Desvantagens: transações entre serviços exigem coordenação; eventos, compensações e atrasos de propagação aumentam a complexidade.

## Performance

Monólito - vantagens: chamadas internas evitam serialização e rede; o fluxo costuma ter menos saltos. Desvantagens: módulos competem pelos mesmos recursos; uma tarefa pesada pode afetar a latência das demais.

Microsserviços - vantagens: um serviço crítico pode ser otimizado e escalado isoladamente; o armazenamento pode ser escolhido conforme o acesso do domínio. Desvantagens: chamadas remotas acrescentam latência; uma cadeia longa de chamadas aumenta a chance de timeouts.

## Testabilidade

Monólito - vantagens: o fluxo completo pode ser executado em um único ambiente; testes transacionais locais são mais fáceis de montar. Desvantagens: uma suíte grande pode ficar lenta; acoplamento entre módulos pode dificultar testes isolados.

Microsserviços - vantagens: contratos pequenos favorecem testes focados; cada serviço pode executar sua suíte de forma independente. Desvantagens: testes de ponta a ponta exigem vários serviços; versões incompatíveis, rede e mensagens precisam ser exercitadas nos testes de integração.
