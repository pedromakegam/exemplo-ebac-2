package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;import org.springframework.http.ResponseEntity;import jakarta.validation.Valid;import java.net.URI;import java.util.List;
 @RestController @RequestMapping("/memelandia/usuarios") public class Controller {
 private final Servico service;public Controller(Servico service){this.service=service;}
 @GetMapping public List<Usuario> listar(){return service.listar();}
 @GetMapping("/{id}") public Usuario buscar(@PathVariable long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<Usuario> cadastrar(@Valid @RequestBody Entrada entrada){var salvo=service.cadastrar(entrada);return ResponseEntity.created(URI.create("/memelandia/usuarios/"+salvo.getId())).body(salvo);}

 }
