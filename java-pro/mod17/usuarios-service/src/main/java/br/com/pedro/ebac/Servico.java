package br.com.pedro.ebac;

import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.List;
 @Service public class Servico {
 private final Repositorio repo;
 public Servico(Repositorio repo){this.repo=repo;}
 public List<Usuario> listar(){return repo.findAll();}
 public Usuario buscar(long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 public Usuario cadastrar(Entrada entrada){return repo.save(new Usuario(entrada));}

 }
