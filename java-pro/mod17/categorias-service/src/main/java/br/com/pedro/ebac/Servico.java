package br.com.pedro.ebac;

import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.List;
 @Service public class Servico {
 private final Repositorio repo;private final Referencias referencias;
 public Servico(Repositorio repo,Referencias referencias){this.repo=repo;this.referencias=referencias;}
 public List<CategoriaMeme> listar(){return repo.findAll();}
 public CategoriaMeme buscar(long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 public CategoriaMeme cadastrar(Entrada entrada){referencias.usuario(entrada.usuarioId());return repo.save(new CategoriaMeme(entrada));}

 }
