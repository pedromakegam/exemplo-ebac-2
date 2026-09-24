package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.Map;
@RestControllerAdvice
public class ErrosApi {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,String>> entradaInvalida(MethodArgumentNotValidException erro) {
        return ResponseEntity.badRequest().body(Map.of("erro","Revise os campos obrigatórios e seus formatos"));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<Map<String,String>> argumentoInvalido(IllegalArgumentException erro) {
        return ResponseEntity.badRequest().body(Map.of("erro","Dados inválidos para a operação"));
    }
}
