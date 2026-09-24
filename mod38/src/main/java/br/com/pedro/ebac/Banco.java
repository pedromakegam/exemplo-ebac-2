package br.com.pedro.ebac;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.annotation.PreDestroy;
import jakarta.persistence.*;
import java.util.Map;
import java.util.function.Function;
@ApplicationScoped
public class Banco {
 private EntityManagerFactory factory;
 private synchronized EntityManagerFactory factory(){
  if(factory==null) factory=Persistence.createEntityManagerFactory("cadastro",Map.of("jakarta.persistence.jdbc.url",env("EBAC_DB_URL"),"jakarta.persistence.jdbc.user",env("EBAC_DB_USER"),"jakarta.persistence.jdbc.password",env("EBAC_DB_PASSWORD")));
  return factory;
 }
 private String env(String name){String value=System.getenv(name);if(value==null || value.isBlank())throw new IllegalStateException("Variável obrigatória ausente: "+name);return value;}
 public <T> T transacao(Function<EntityManager,T> operation){
  EntityManager em=factory().createEntityManager();
  try {em.getTransaction().begin();T value=operation.apply(em);em.getTransaction().commit();return value;}
  catch(RuntimeException error){if(em.getTransaction().isActive())em.getTransaction().rollback();throw error;}
  finally{em.close();}
 }
 @PreDestroy public synchronized void fechar(){if(factory!=null)factory.close();}
}
