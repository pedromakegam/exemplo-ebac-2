# Expressões lambda

## O que são

Uma expressão lambda permite passar um comportamento como argumento com menos código. Em Java, ela implementa o único método abstrato de uma interface funcional. Em (a, b) -> a + b, os valores à esquerda são os parâmetros e a expressão à direita calcula o resultado.

## Uso na prática

Um exemplo é Predicate<Integer> par = numero -> numero % 2 == 0. O método par.test(8) retorna true. Outro uso é lista.stream().filter(pessoa -> pessoa.getIdade() >= 18).toList(), que cria uma lista filtrada sem escrever o laço e a acumulação manualmente.

## Diferença para o código tradicional

O comportamento também poderia ficar em um método ou em uma classe anônima. A lambda reduz essa estrutura quando a operação é pequena e pode ser passada como um valor. Ela não torna o código automaticamente mais rápido. Quando a expressão começa a ter muitas condições ou efeitos colaterais, um método com nome claro costuma ser mais fácil de entender.

## Referência

Oracle - Lambda Expressions. https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
