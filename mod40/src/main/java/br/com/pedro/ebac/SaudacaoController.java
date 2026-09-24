package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
public class SaudacaoController {
    @GetMapping("/saudacao")
    public Map<String,String> saudar(@RequestParam(defaultValue="Mundo") String nome) {
        if(nome.isBlank() || nome.length()>100) throw new IllegalArgumentException("Nome inválido");
        return Map.of("mensagem","Olá, "+nome.trim()+"!");
    }
}
