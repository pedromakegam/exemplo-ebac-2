package br.com.pedro.ebac;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
@ApplicationScoped public class ClienteRepository {
 @Inject Banco banco;
 public List<Cliente> listar(){return banco.transacao(em->em.createQuery("select c from Cliente c order by c.id",Cliente.class).getResultList());}
 public void salvar(Long id,String nome,String email){banco.transacao(em->{Cliente c=id==null?new Cliente():em.find(Cliente.class,id);if(c==null)throw new IllegalArgumentException("Cliente não encontrado");c.setNome(nome);c.setEmail(email);if(id==null)em.persist(c);return null;});}
 public void excluir(Long id){banco.transacao(em->{Cliente c=em.find(Cliente.class,id);if(c==null)throw new IllegalArgumentException("Cliente não encontrado");em.remove(c);return null;});}
}
