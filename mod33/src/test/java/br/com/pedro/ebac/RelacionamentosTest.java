package br.com.pedro.ebac;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL",matches="jdbc:postgresql:.*")
class RelacionamentosTest {
    static EntityManagerFactory factory;
    @BeforeAll static void preparar() throws Exception {factory=Jpa.prepararTeste();}
    @AfterAll static void fechar() {if(factory!=null) factory.close();}
    @Test void compartilhaMarcaEAcessorioSemDuplicarEntidades() {
        Long[] ids=Jpa.transacao(factory,em->{
            Marca marca=new Marca("Marca Exemplo");Acessorio ar=new Acessorio("Ar condicionado");em.persist(marca);em.persist(ar);
            Carro a=new Carro("Modelo A",marca),b=new Carro("Modelo B",marca);a.adicionar(ar);b.adicionar(ar);em.persist(a);em.persist(b);
            return new Long[]{a.getId(),b.getId(),marca.getId(),ar.getId()};
        });
        Jpa.transacao(factory,em->{
            Carro a=em.find(Carro.class,ids[0]),b=em.find(Carro.class,ids[1]);
            assertEquals(ids[2],a.getMarca().getId());assertEquals(ids[2],b.getMarca().getId());
            assertEquals(ids[3],a.getAcessorios().iterator().next().getId());
            assertEquals(ids[3],b.getAcessorios().iterator().next().getId());
            em.remove(a);return null;
        });
        Jpa.transacao(factory,em->{assertNotNull(em.find(Marca.class,ids[2]));assertNotNull(em.find(Acessorio.class,ids[3]));assertEquals(1,em.find(Carro.class,ids[1]).getAcessorios().size());return null;});
    }
}
