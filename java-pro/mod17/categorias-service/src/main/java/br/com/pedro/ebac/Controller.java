package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;import org.springframework.http.ResponseEntity;import jakarta.validation.Valid;import java.net.URI;import java.util.List;
 @RestController @RequestMapping("/memelandia/categorias") public class Controller {
 private final Servico service;public Controller(Servico service){this.service=service;}
 @GetMapping public List<CategoriaMeme> listar(){return service.listar();}
 @GetMapping("/{id}") public CategoriaMeme buscar(@PathVariable long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<CategoriaMeme> cadastrar(@Valid @RequestBody Entrada entrada){var salvo=service.cadastrar(entrada);return ResponseEntity.created(URI.create("/memelandia/categorias/"+salvo.getId())).body(salvo);}

 }
