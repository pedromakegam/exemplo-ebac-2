package br.com.pedro.ebac;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@Service
public class ClienteService {
    private final ClienteRepository repo;
    public ClienteService(ClienteRepository repo) {this.repo=repo;}
    @Transactional(readOnly=true) public List<Cliente> listar() {return repo.findAll();}
    @Transactional(readOnly=true) public Cliente buscar(long id) {return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
    @Transactional public Cliente cadastrar(EntradaCliente entrada) {return repo.save(new Cliente(entrada));}
    @Transactional public Cliente atualizar(long id,EntradaCliente entrada) {Cliente atual=buscar(id);atual.alterar(entrada);return atual;}
    @Transactional public void excluir(long id) {repo.delete(buscar(id));}
}
