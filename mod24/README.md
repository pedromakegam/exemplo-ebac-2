# DAO e Service - testes de contrato

Implementação do zero dos contratos de cadastro, busca, atualização e exclusão. Cliente é imutável e o armazenamento em memória é isolado por instância. Os testes usam dados fictícios.

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod24 -am test
```

## Ciclo dos testes

Os dez testes de DAO e Service foram executados com os métodos ainda sem implementação: a suíte falhou. Depois foram implementadas busca, atualização, exclusão e gravação, e a suíte passou. Casos verificados: registro existente e ausente, duplicidade sem sobrescrita e exclusão repetida. O CPF mantém os zeros iniciais em String; a validação deste exercício confere o formato, sem calcular dígitos verificadores.
