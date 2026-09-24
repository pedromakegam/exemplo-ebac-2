package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import jakarta.validation.Valid;
import java.util.List;
import java.net.URI;
@RestController @RequestMapping("/clientes")
public class ClienteController {
    private final ClienteService service;
    public ClienteController(ClienteService service) {this.service=service;}
    @GetMapping public List<Cliente> listar() {return service.listar();}
    @GetMapping("/{id}") public Cliente buscar(@PathVariable long id) {return service.buscar(id);}
    @PostMapping public ResponseEntity<Cliente> criar(@Valid @RequestBody EntradaCliente entrada) {
        Cliente salvo=service.cadastrar(entrada);return ResponseEntity.created(URI.create("/clientes/"+salvo.getId())).body(salvo);
    }
    @PutMapping("/{id}") public Cliente atualizar(@PathVariable long id,@Valid @RequestBody EntradaCliente entrada) {return service.atualizar(id,entrada);}
    @DeleteMapping("/{id}") public ResponseEntity<Void> excluir(@PathVariable long id) {service.excluir(id);return ResponseEntity.noContent().build();}
}
