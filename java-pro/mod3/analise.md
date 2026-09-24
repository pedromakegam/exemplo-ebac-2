# Estratégias utilizadas

No backtracking, acrescento um elemento, exploro as possibilidades a partir dele e retiro o elemento ao voltar. O próximo índice sempre avança, evitando permutações repetidas. Quando faltam elementos para completar k, o laço para. São C(n,k) resultados, cada um com k elementos; materializar a saída exige Θ(k × C(n,k)) espaço e pelo menos esse trabalho, para 1 <= k <= n. A recursão e a combinação em construção usam O(k) espaço auxiliar, sem contar o resultado e a validação dos valores distintos.

Para o troco, escolho primeiro a maior moeda que cabe no valor restante. Com 18, o resultado é [5, 5, 5, 2, 1], totalizando cinco moedas. Para o conjunto 5, 2 e 1, essa estratégia produz o menor número de moedas. O teste compara o resultado com programação dinâmica para todos os valores de 0 a 100. O tempo e o espaço são O(m), em que m é o número de moedas devolvidas.

Isso não vale para qualquer conjunto de moedas. Com moedas 4, 3 e 1, o guloso devolve 4 + 1 + 1 para o valor 6, mas 3 + 3 usa menos moedas. Para denominações arbitrárias, usaria programação dinâmica para garantir a solução ótima, ou demonstraria antes que o sistema admite a escolha gulosa.
