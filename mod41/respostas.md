# Microsserviços

## Ideia principal

Na arquitetura de microsserviços, a aplicação é dividida em serviços com responsabilidades de negócio bem delimitadas. Cada serviço pode ter seu próprio processo de implantação e controlar seus dados. Em um sistema de vendas, cadastro de clientes e catálogo de produtos podem ser domínios distintos, desde que essa separação faça sentido para o negócio.

## Benefícios e custos

A divisão permite publicar e escalar partes do sistema de forma independente. Também ajuda equipes diferentes a trabalhar em seus próprios domínios. Em troca, surgem chamadas de rede, falhas parciais e dados distribuídos. Uma requisição pode funcionar em um serviço e falhar em outro. É preciso definir timeouts, lidar com repetição de mensagens e acompanhar o fluxo com logs e métricas.

## Critério de escolha

Microsserviços não são uma obrigação para todo projeto. Em uma aplicação pequena, um monólito bem modularizado pode entregar o mesmo resultado com menos esforço operacional. A separação passa a valer a pena quando existe uma necessidade concreta, como equipes independentes, ritmos de entrega diferentes ou um domínio que precisa de escala própria.

## Referência

Chris Richardson - Microservice Architecture pattern. https://microservices.io/patterns/microservices.html
