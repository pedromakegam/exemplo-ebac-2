package br.com.pedro.ebac;

import java.util.Optional;
public interface IClienteDAO {
    void salvar(Cliente cliente);
    Optional<Cliente> buscar(String cpf);
    void atualizar(Cliente cliente);
    boolean excluir(String cpf);
}
