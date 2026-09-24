package br.com.pedro.ebac;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import java.util.List;
@Service
public class ProdutoService {
    private final ProdutoRepository repo;
    public ProdutoService(ProdutoRepository repo) {this.repo=repo;}
    @Transactional(readOnly=true) public List<Produto> listar() {return repo.findAll();}
    @Transactional(readOnly=true) public Produto buscar(long id) {return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
    @Transactional public Produto cadastrar(EntradaProduto entrada) {return repo.save(new Produto(entrada));}
    @Transactional public Produto atualizar(long id,EntradaProduto entrada) {Produto atual=buscar(id);atual.alterar(entrada);return atual;}
    @Transactional public void excluir(long id) {repo.delete(buscar(id));}
}
