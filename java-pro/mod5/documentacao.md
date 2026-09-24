# Quando documentar

Um nome claro já explica boa parte da intenção de um método. Não faz sentido acrescentar um comentário dizendo apenas que adicionar soma dois valores. A documentação ganha utilidade quando registra o contrato: parâmetros aceitos, índice inicial da sequência, erros e limitações que o nome não revela.

Em Fibonacci, é importante esclarecer que a sequência começa em F(0)=0 e F(1)=1, porque existem outras convenções de indexação. Na divisão, explico que zero lança uma exceção, em vez de retornar infinito como uma divisão double sem validação faria. Também deixei explícito que a calculadora usa ponto flutuante e não é uma implementação de cálculos monetários.

Neste exercício, os métodos têm JavaDoc para praticar a ferramenta. Em um projeto maior, documentaria principalmente contratos públicos e decisões menos óbvias, mantendo a documentação junto do código e atualizada quando o comportamento mudar. Os testes mostram exemplos executáveis, mas não substituem toda a explicação do contrato.
