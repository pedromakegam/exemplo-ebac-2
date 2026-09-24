package br.com.pedro.ebac;

import java.util.Optional;
import java.util.Objects;
public class ClienteService implements IClienteService {
    private final IClienteDAO dao;
    public ClienteService(IClienteDAO dao) { this.dao = Objects.requireNonNull(dao); }
    public void salvar(Cliente cliente) { dao.salvar(Objects.requireNonNull(cliente)); }
    public Optional<Cliente> buscar(String cpf) { return dao.buscar(Objects.requireNonNull(cpf)); }
    public void atualizar(Cliente cliente) { dao.atualizar(Objects.requireNonNull(cliente)); }
    public boolean excluir(String cpf) { return dao.excluir(Objects.requireNonNull(cpf)); }
}
