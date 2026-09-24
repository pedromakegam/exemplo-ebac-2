package br.com.pedro.ebac;

import jakarta.persistence.EntityManager;
import java.util.Map;
import java.util.NoSuchElementException;
public final class VendaService {
    private final EntityManager em;
    public VendaService(EntityManager em) {this.em=em;}
    public Venda cadastrar(long clienteId,Map<Long,Integer> itens) {
        if(itens==null || itens.isEmpty()) throw new IllegalArgumentException("Venda sem itens");
        Cliente cliente=em.find(Cliente.class,clienteId);
        if(cliente==null) throw new NoSuchElementException("Cliente não encontrado");
        Venda venda=new Venda(cliente);
        for(var item:itens.entrySet()) {
            if(item.getKey()==null || item.getValue()==null) throw new IllegalArgumentException("Item inválido");
            Produto produto=em.find(Produto.class,item.getKey());
            if(produto==null) throw new NoSuchElementException("Produto não encontrado");
            venda.adicionar(produto,item.getValue());
        }
        em.persist(venda);return venda;
    }
}
