package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.Valid;import jakarta.validation.constraints.*;
import java.net.URI;import java.time.LocalDate;import java.util.List;
@RestController public class AbrigoController {
 private final AbrigoService service;public AbrigoController(AbrigoService service){this.service=service;}
 public record NovoFuncionario(@NotBlank @Size(max=100) String nome){}
 public record NovaRaca(@NotBlank @Size(max=100) String nome,@NotNull Especie especie){}
 public record NovoAnimal(@NotBlank @Size(max=100) String nome,@NotNull @Positive Long racaId,@NotNull @Positive Long funcionarioId,@NotNull @PastOrPresent LocalDate dataResgate){}
 @GetMapping("/funcionarios") public List<Funcionario> funcionarios(){return service.funcionarios();}
 @PostMapping("/funcionarios") public ResponseEntity<Funcionario> funcionario(@Valid @RequestBody NovoFuncionario entrada){Funcionario f=service.funcionario(entrada.nome());return ResponseEntity.created(URI.create("/funcionarios/"+f.getId())).body(f);}
 @GetMapping("/racas") public List<Raca> racas(){return service.racas();}
 @PostMapping("/racas") public ResponseEntity<Raca> raca(@Valid @RequestBody NovaRaca entrada){Raca r=service.raca(entrada.nome(),entrada.especie());return ResponseEntity.created(URI.create("/racas/"+r.getId())).body(r);}
 @GetMapping("/animais") public List<Animal> animais(){return service.animais();}
 @PostMapping("/animais") public ResponseEntity<Animal> animal(@Valid @RequestBody NovoAnimal entrada){Animal a=service.resgatar(entrada.nome(),entrada.racaId(),entrada.funcionarioId(),entrada.dataResgate());return ResponseEntity.created(URI.create("/animais/"+a.getId())).body(a);}
 @GetMapping("/relatorios/resgates") public List<TotalResgates> relatorio(@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate inicio,@RequestParam @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate fim){return service.totais(inicio,fim);}
}
