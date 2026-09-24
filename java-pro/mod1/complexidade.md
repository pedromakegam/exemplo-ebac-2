# Complexidade

n é a quantidade de elementos; os valores são inteiros de tamanho fixo.

| Estrutura/método | Tempo | Espaço auxiliar por chamada |
|---|---|---|
| Pilha.push | O(1) amortizado; O(n) quando amplia o vetor | O(1), ou O(n) durante a ampliação |
| Pilha.pop, top, size, isEmpty | O(1) | O(1) |
| Fila.enqueue, dequeue, rear, front, size, isEmpty | O(1) | O(1) |
| Lista.push | O(1), pois guarda referência para o último nó | O(1) |
| Lista.pop | O(n), pois procura o penúltimo nó | O(1) |
| Lista.insert | O(n) no caso geral; O(1) no início/fim | O(1) |
| Lista.remove, elementAt | O(n) no pior caso; O(1) no início | O(1) |
| Lista.size | O(1) | O(1) |
| Lista.printList/toString | O(n) | O(n), pela string construída |

A fila e a lista ocupam O(n) no total. A pilha conserva o vetor depois de retirar elementos; por isso, seu espaço depende do maior tamanho já alcançado, não apenas da quantidade atual. Dobrar o vetor evita copiar todos os elementos em cada push. Uma lista duplamente encadeada permitiria pop em O(1), ao custo de outra referência por nó.
