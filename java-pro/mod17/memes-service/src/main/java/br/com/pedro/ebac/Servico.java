package br.com.pedro.ebac;

import org.springframework.stereotype.Service;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.List;
 @Service public class Servico {
 private final Repositorio repo;private final Referencias referencias;
 public Servico(Repositorio repo,Referencias referencias){this.repo=repo;this.referencias=referencias;}
 public List<Meme> listar(){return repo.findAll();}
 public Meme buscar(long id){return repo.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 public Meme cadastrar(Entrada entrada){referencias.usuario(entrada.usuarioId());referencias.categoria(entrada.categoriaId());validarUrl(entrada.url());return repo.save(new Meme(entrada));}
 public static void validarUrl(String value){java.net.URI uri=java.net.URI.create(value);if(!java.util.Set.of("http","https").contains(uri.getScheme()==null?"":uri.getScheme()) || uri.getHost()==null || uri.getUserInfo()!=null)throw new IllegalArgumentException("Informe uma URL HTTP ou HTTPS válida");}
 public Meme aleatorio(){long count=repo.count();if(count==0)throw new ResponseStatusException(HttpStatus.NOT_FOUND);int index=java.util.concurrent.ThreadLocalRandom.current().nextInt(Math.toIntExact(count));return repo.findAll(org.springframework.data.domain.PageRequest.of(index,1,org.springframework.data.domain.Sort.by("id"))).getContent().get(0);}
 }
