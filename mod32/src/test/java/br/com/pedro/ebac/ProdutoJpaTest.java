package br.com.pedro.ebac;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import jakarta.persistence.*;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL",matches="jdbc:postgresql:.*")
class ProdutoJpaTest {
    static EntityManagerFactory factory;
    @BeforeAll static void preparar() throws Exception { factory=Jpa.prepararTeste(); }
    @AfterAll static void fechar() { if(factory!=null) factory.close(); }
    @Test void criaTabelaEPersisteProduto() {
        Long id=Jpa.transacao(factory,em->{Produto p=new Produto("P001","Caderno",new BigDecimal("15.90"),"Papelaria");em.persist(p);return p.getId();});
        Jpa.transacao(factory,em->{
            Produto p=em.find(Produto.class,id);assertEquals("Caderno",p.getNome());assertEquals("P001",p.getCodigo());
            assertEquals(0,new BigDecimal("15.90").compareTo(p.getPreco()));
            Number linhas=(Number)em.createNativeQuery("SELECT COUNT(*) FROM "+em.getEntityManagerFactory().getProperties().get("hibernate.default_schema")+".produto WHERE id=:id").setParameter("id",id).getSingleResult();
            assertEquals(1,linhas.intValue());return null;
        });
    }
    @Test void atualizaERemoveProduto() {
        Long id=Jpa.transacao(factory,em->{Produto p=new Produto("P002","Caneta",new BigDecimal("3.50"),"Papelaria");em.persist(p);return p.getId();});
        Jpa.transacao(factory,em->{em.find(Produto.class,id).alterarPreco(new BigDecimal("4.00"));return null;});
        Jpa.transacao(factory,em->{Produto p=em.find(Produto.class,id);assertEquals(0,new BigDecimal("4.00").compareTo(p.getPreco()));em.remove(p);return null;});
        assertNull(Jpa.transacao(factory,em->em.find(Produto.class,id)));
    }
}
