package br.com.pedro.ebac;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL",matches="jdbc:postgresql:.*")
class ConcorrenciaTest {
    @Test void detectaAtualizacaoBaseadaEmVersaoAntiga() throws Exception {
        try(EntityManagerFactory factory=Jpa.prepararTeste()) {
            Long id=Jpa.transacao(factory,em->{Produto p=new Produto("CONC","Teste",new BigDecimal("10.00"),"Teste");em.persist(p);return p.getId();});
            try(EntityManager a=factory.createEntityManager();EntityManager b=factory.createEntityManager()) {
                a.getTransaction().begin();b.getTransaction().begin();
                Produto pa=a.find(Produto.class,id),pb=b.find(Produto.class,id);
                pa.alterarPreco(new BigDecimal("11.00"));pb.alterarPreco(new BigDecimal("12.00"));
                a.getTransaction().commit();assertThrows(RollbackException.class,()->b.getTransaction().commit());
            }
            Jpa.transacao(factory,em->{assertEquals(0,new BigDecimal("11.00").compareTo(em.find(Produto.class,id).getPreco()));return null;});
        }
    }
}
