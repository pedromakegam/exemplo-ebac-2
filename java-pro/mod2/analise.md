# Análise dos algoritmos

O caso base do fatorial é 0! = 1. Para n positivo, n! = n × (n - 1)!. A versão recursiva segue essa definição. A top-down guarda os resultados em um vetor; a bottom-up começa em 1 e multiplica até n.

Fatorial não tem subproblemas repetidos dentro de uma única chamada: cada valor é calculado uma vez. A memoização não reduz a quantidade de multiplicações nessa situação e usa mais memória. Um cache reaproveitado entre consultas poderia ajudar quando vários fatoriais fossem pedidos.

Valores acima de 100 não falham obrigatoriamente por causa da recursão. O problema imediato, se fosse usado int ou long, seria overflow: 13! já não cabe em int e 21! já não cabe em long. BigInteger representa valores maiores, limitados pela memória disponível. O teste calcula 101! nas três versões. Recursão muito profunda ainda pode causar StackOverflowError; a bottom-up evita esse uso da pilha.

Contando operações aritméticas como unitárias, as três versões do fatorial fazem O(n) multiplicações. A recursiva usa O(n) chamadas na pilha; top-down usa O(n) chamadas e O(n) entradas no vetor; bottom-up mantém O(1) variáveis. Com BigInteger, cada número cresce: n! ocupa Θ(n log n) bits. Portanto, O(1) variáveis não significa O(1) bytes, e o custo real das multiplicações também depende dos bits dos operandos.

Fibonacci ingênuo recalcula os mesmos valores: para F(n), ele chama F(n-1) e F(n-2). Seu tempo é Θ(phi^n), frequentemente limitado por O(2^n), e a pilha ocupa O(n). A versão dinâmica usada aqui faz O(n) somas e mantém apenas os dois últimos termos. No modelo de inteiros de tamanho fixo, o espaço auxiliar é O(1). Com BigInteger, F(n) tem Θ(n) bits; a memória é O(n) bits e o custo acumulado das somas é O(n²) bits. A versão ingênua foi limitada a 40 na demonstração para não prender a execução.
