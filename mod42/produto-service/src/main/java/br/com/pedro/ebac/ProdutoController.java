package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import java.net.URI;
@RestController @RequestMapping("/produtos")
public class ProdutoController {
    private final ProdutoService service;
    public ProdutoController(ProdutoService service) {this.service=service;}
    @GetMapping public List<Produto> listar() {return service.listar();}
    @GetMapping("/{id}") public Produto buscar(@PathVariable long id) {return service.buscar(id);}
    @PostMapping public ResponseEntity<Produto> criar(@Valid @RequestBody EntradaProduto entrada) {
        Produto salvo=service.cadastrar(entrada);return ResponseEntity.created(URI.create("/produtos/"+salvo.getId())).body(salvo);
    }
    @PutMapping("/{id}") public Produto atualizar(@PathVariable long id,@Valid @RequestBody EntradaProduto entrada) {return service.atualizar(id,entrada);}
    @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable long id) {service.excluir(id);return ResponseEntity.noContent().build();}
}
