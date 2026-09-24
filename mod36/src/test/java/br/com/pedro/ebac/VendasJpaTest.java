package br.com.pedro.ebac;

import jakarta.persistence.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import java.math.BigDecimal;
import java.util.Map;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
@EnabledIfEnvironmentVariable(named="EBAC_DB_URL",matches="jdbc:postgresql:.*")
class VendasJpaTest {
    static EntityManagerFactory factory;
    @BeforeAll static void preparar() throws Exception {factory=Jpa.prepararTeste();}
    @AfterAll static void fechar() {if(factory!=null)factory.close();}
    private Long[] cadastrar(String codigo) {
        return Jpa.transacao(factory,em->{
            Cliente cliente=new Cliente(codigo.equals("VENDA")?"00000000001":"00000000002","Cliente Exemplo","cliente@example.invalid");
            Produto produto=new Produto(codigo,"Caderno",new BigDecimal("12.50"),"Papelaria");
            new JpaDAO<>(em,Cliente.class).salvar(cliente);new JpaDAO<>(em,Produto.class).salvar(produto);
            return new Long[]{cliente.getId(),produto.getId()};
        });
    }
    @Test void persisteVendaComCascadeEPrecoHistorico() {
        Long[] ids=cadastrar("VENDA");Long venda=Jpa.transacao(factory,em->new VendaService(em).cadastrar(ids[0],Map.of(ids[1],2)).getId());
        Jpa.transacao(factory,em->{em.find(Produto.class,ids[1]).alterarPreco(new BigDecimal("20.00"));return null;});
        Jpa.transacao(factory,em->{Venda v=em.find(Venda.class,venda);assertEquals(1,v.getItens().size());assertEquals(0,new BigDecimal("25.00").compareTo(v.getTotal()));return null;});
    }
    @Test void falhaDesfazTransacao() {
        Long[] ids=cadastrar("ERRO");Long antes=Jpa.transacao(factory,em->em.createQuery("select count(v) from Venda v",Long.class).getSingleResult());
        assertThrows(NoSuchElementException.class,()->Jpa.transacao(factory,em->new VendaService(em).cadastrar(ids[0],Map.of(-1L,2))));
        Long depois=Jpa.transacao(factory,em->em.createQuery("select count(v) from Venda v",Long.class).getSingleResult());assertEquals(antes,depois);
    }
    @Test void crudGenericoDeCliente() {
        Long id=Jpa.transacao(factory,em->{Cliente c=new Cliente("00000000003","Cliente CRUD","crud@example.invalid");new JpaDAO<>(em,Cliente.class).salvar(c);return c.getId();});
        Cliente separado=Jpa.transacao(factory,em->new JpaDAO<>(em,Cliente.class).buscar(id).orElseThrow());separado.alterarEmail("novo@example.invalid");
        Jpa.transacao(factory,em->{new JpaDAO<>(em,Cliente.class).atualizar(separado);return null;});
        Jpa.transacao(factory,em->{var dao=new JpaDAO<>(em,Cliente.class);assertEquals("novo@example.invalid",dao.buscar(id).orElseThrow().getEmail());assertFalse(dao.buscarTodos().isEmpty());assertTrue(dao.excluir(id));return null;});
        boolean ausente = Jpa.transacao(factory,em->new JpaDAO<>(em,Cliente.class).buscar(id).isEmpty());
                assertTrue(ausente);
    }
}
