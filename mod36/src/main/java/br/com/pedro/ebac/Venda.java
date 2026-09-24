package br.com.pedro.ebac;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;
@Entity @Table(name="venda")
public class Venda {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="cliente_id",nullable=false) private Cliente cliente;
    @Column(nullable=false) private Instant criadaEm=Instant.now();
    @OneToMany(mappedBy="venda",cascade=CascadeType.ALL,orphanRemoval=true) private List<ItemVenda> itens=new ArrayList<>();
    protected Venda() { }
    public Venda(Cliente cliente) {this.cliente=Objects.requireNonNull(cliente);}
    public void adicionar(Produto produto,int quantidade) {itens.add(new ItemVenda(this,produto,quantidade));}
    public Long getId() {return id;}
    public List<ItemVenda> getItens() {return List.copyOf(itens);}
    public BigDecimal getTotal() {return itens.stream().map(ItemVenda::getSubtotal).reduce(BigDecimal.ZERO,BigDecimal::add);}
}
