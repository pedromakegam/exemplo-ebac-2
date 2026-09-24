package br.com.pedro.ebac;

import org.springframework.stereotype.Component;
 import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;
 import org.springframework.beans.factory.annotation.Value;
 import java.net.*;import java.net.http.*;import java.time.Duration;
 @Component public class Referencias {
 private final URI usuarios,categorias;
 private final HttpClient client=HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).followRedirects(HttpClient.Redirect.NEVER).build();
 public Referencias(@Value("${USUARIOS_URL:http://127.0.0.1:18101}") String usuarios,@Value("${CATEGORIAS_URL:http://127.0.0.1:18102}") String categorias){this.usuarios=validarBase(usuarios);this.categorias=validarBase(categorias);}
 static URI validarBase(String value){URI uri=URI.create(value);boolean local=java.util.Set.of("127.0.0.1","localhost","[::1]").contains(uri.getHost()==null?"":uri.getHost());if(uri.getHost()==null || uri.getUserInfo()!=null || uri.getQuery()!=null || uri.getFragment()!=null || !("https".equals(uri.getScheme()) || local && "http".equals(uri.getScheme())))throw new IllegalArgumentException("Use HTTPS para serviços remotos");return uri;}
 public void usuario(long id){checar(usuarios,"/memelandia/usuarios/",id);}
 public void categoria(long id){checar(categorias,"/memelandia/categorias/",id);}
 private void checar(URI base,String rota,long id){
 if(id<=0)throw new IllegalArgumentException("Identificador inválido");
 var req=HttpRequest.newBuilder(base.resolve(rota+id)).timeout(Duration.ofSeconds(3)).GET().build();
 try {var res=client.send(req,HttpResponse.BodyHandlers.discarding());if(res.statusCode()==404)throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,"Referência inexistente");if(res.statusCode()!=200)throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Serviço de referência indisponível");}
 catch(InterruptedException e){Thread.currentThread().interrupt();throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Consulta interrompida");}
 catch(java.io.IOException e){throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE,"Serviço de referência indisponível");}
 }
 }
