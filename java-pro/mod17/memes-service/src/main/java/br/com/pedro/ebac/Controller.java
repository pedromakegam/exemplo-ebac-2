package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;import org.springframework.http.ResponseEntity;import jakarta.validation.Valid;import java.net.URI;import java.util.List;
 @RestController @RequestMapping("/memelandia/memes") public class Controller {
 private final Servico service;public Controller(Servico service){this.service=service;}
 @GetMapping public List<Meme> listar(){return service.listar();}
 @GetMapping("/{id}") public Meme buscar(@PathVariable long id){return service.buscar(id);}
 @PostMapping public ResponseEntity<Meme> cadastrar(@Valid @RequestBody Entrada entrada){var salvo=service.cadastrar(entrada);return ResponseEntity.created(URI.create("/memelandia/memes/"+salvo.getId())).body(salvo);}
 @GetMapping("/aleatorio") public Meme aleatorio(){return service.aleatorio();}
 }
