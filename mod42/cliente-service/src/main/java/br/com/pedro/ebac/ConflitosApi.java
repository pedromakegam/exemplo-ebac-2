package br.com.pedro.ebac;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.*;
import org.springframework.dao.DataIntegrityViolationException;
import java.util.Map;
@RestControllerAdvice
public class ConflitosApi {
    @ExceptionHandler(DataIntegrityViolationException.class)
    ResponseEntity<Map<String,String>> conflito(DataIntegrityViolationException erro) {
        return ResponseEntity.status(409).body(Map.of("erro","Registro duplicado ou referência em uso"));
    }
}
