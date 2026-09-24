package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;import com.sun.net.httpserver.HttpServer;import java.net.*;import static org.junit.jupiter.api.Assertions.*;import org.springframework.web.server.ResponseStatusException;
 class ReferenciasTest {
 @Test void distingueReferenciaAusenteDeFalhaRemota() throws Exception {
 var server=HttpServer.create(new InetSocketAddress("127.0.0.1",0),0);server.createContext("/memelandia/usuarios/1",exchange->{exchange.sendResponseHeaders(200,-1);exchange.close();});server.createContext("/memelandia/usuarios/2",exchange->{exchange.sendResponseHeaders(404,-1);exchange.close();});server.createContext("/memelandia/usuarios/3",exchange->{exchange.sendResponseHeaders(503,-1);exchange.close();});server.start();
 try{String url="http://127.0.0.1:"+server.getAddress().getPort();var r=new Referencias(url,url);assertDoesNotThrow(()->r.usuario(1));assertEquals(422,assertThrows(ResponseStatusException.class,()->r.usuario(2)).getStatusCode().value());assertEquals(503,assertThrows(ResponseStatusException.class,()->r.usuario(3)).getStatusCode().value());}finally{server.stop(0);}
 }
 @Test void rejeitaHttpRemoto(){assertThrows(IllegalArgumentException.class,()->Referencias.validarBase("http://example.com"));}
 @Test void aceitaHttps(){assertDoesNotThrow(()->Referencias.validarBase("https://example.com"));}
 }
