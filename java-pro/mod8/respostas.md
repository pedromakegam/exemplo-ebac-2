# Contêineres e entrega de software

## 1. Contêineres e microsserviços

Contêiner é uma forma de empacotar e executar uma aplicação com suas dependências, isolando seu ambiente de execução. Microsserviços são uma decisão de arquitetura: separar o sistema por responsabilidades de negócio. Um monólito pode rodar em contêiner, e um microsserviço pode rodar diretamente em uma máquina virtual. Portanto, usar Docker não transforma uma aplicação em microsserviços. É possível executar vários serviços em máquinas virtuais, desde que portas, recursos, configuração e isolamento sejam administrados.

## 2. Integração contínua

Integrar alterações pequenas e frequentes reduz o tempo em que cada pessoa trabalha sobre uma versão diferente do código. Isso facilita descobrir conflitos e incompatibilidades cedo. O merge deve disparar compilação e testes. Apenas juntar código com frequência, sem verificar o resultado, não garante uma integração saudável.

## 3. Entrega contínua e testes

Na entrega contínua, cada mudança aprovada deve poder chegar à produção. Uma cobertura de testes abrangente ajuda a verificar regras, integrações e casos de erro antes da liberação. A porcentagem de linhas executadas, sozinha, não prova qualidade: o importante é que os testes detectem falhas relevantes. Também são necessários testes de implantação, configuração e migrações.

## 4. Entrega e implantação contínuas

Na entrega contínua, a versão fica pronta para produção, mas a liberação pode depender de uma decisão humana. Na implantação contínua, as mudanças que passam pelas verificações seguem automaticamente para produção. Esta última exige confiança nos testes, monitoramento e uma forma segura de reverter ou corrigir problemas.

## Referências

Docker - What is Docker? https://docs.docker.com/get-started/docker-overview/
Martin Fowler - Continuous Delivery. https://martinfowler.com/bliki/ContinuousDelivery.html
