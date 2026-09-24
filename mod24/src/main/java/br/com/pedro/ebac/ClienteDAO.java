package br.com.pedro.ebac;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
public class ClienteDAO implements IClienteDAO {
    private final Map<String, Cliente> clientes = new HashMap<>();
    public void salvar(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (clientes.putIfAbsent(cliente.cpf(), cliente) != null) {
            throw new IllegalStateException("Cliente já cadastrado");
        }
    }
    public Optional<Cliente> buscar(String cpf) {
        return Optional.ofNullable(clientes.get(Objects.requireNonNull(cpf)));
    }
    public void atualizar(Cliente cliente) {
        Objects.requireNonNull(cliente);
        if (clientes.replace(cliente.cpf(), cliente) == null) {
            throw new NoSuchElementException("Cliente não encontrado");
        }
    }
    public boolean excluir(String cpf) {
        return clientes.remove(Objects.requireNonNull(cpf)) != null;
    }
}
