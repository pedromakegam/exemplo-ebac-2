# Projeto 1 - cadastro de clientes

Projeto de cadastro em memória baseado no exemplo CadastroCliente das aulas de Rodrigo Pires. Mantém as operações cadastrar, consultar, alterar, excluir e listar. A interface usa JOptionPane. Foram adicionados Maven e testes das operações do DAO.

Base: https://github.com/digaomilleniun/backend-java-ebac/tree/main/mod14/CadastroCliente

Requer JDK 17 e Maven. Execute a partir da raiz do repositório:

```sh
mvn -pl mod14 -am test
```

Para executar a demonstração depois de compilar:

```sh
java -Dfile.encoding=UTF-8 -cp mod14/target/classes br.com.rpires.App
```
