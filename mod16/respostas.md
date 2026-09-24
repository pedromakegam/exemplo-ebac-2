# Injeção de dependências

## Como funciona

Injeção de dependências é receber de fora os objetos que uma classe precisa para trabalhar. Por exemplo, um serviço de cadastro recebe um repositório pelo construtor, em vez de criar a conexão com o banco dentro do próprio serviço. A classe continua responsável pela regra de cadastro, mas a escolha e a montagem do repositório ficam fora dela.

## Exemplo com Spring

No Spring, o contêiner cria os objetos registrados como beans e resolve suas dependências. Um ClienteService pode receber um ClienteRepository no construtor. Na aplicação, entra o repositório real; no teste, pode entrar uma implementação em memória. A regra de negócio não precisa mudar para fazer essa troca.

## Por que usar

A vantagem é reduzir o acoplamento e deixar as dependências explícitas. Prefiro o construtor para uma dependência obrigatória: o objeto já nasce pronto para uso. Isso não significa criar uma interface para tudo. A interface faz sentido quando existe um contrato útil, uma implementação alternativa ou necessidade de isolar uma dependência no teste.

## Referência

Spring Framework - Dependency Injection. https://docs.spring.io/spring-framework/reference/core/beans/dependencies/factory-collaborators.html
