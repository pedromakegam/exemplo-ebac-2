package br.com.pedro.ebac;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
@Entity @Table(name="item_venda")
public class ItemVenda {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="venda_id",nullable=false) private Venda venda;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="produto_id",nullable=false) private Produto produto;
    @Column(nullable=false) private int quantidade;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal precoUnitario;
    protected ItemVenda() { }
    public ItemVenda(Venda venda,Produto produto,int quantidade) {
        if(quantidade<=0) throw new IllegalArgumentException("Quantidade deve ser positiva");
        this.venda=Objects.requireNonNull(venda);this.produto=Objects.requireNonNull(produto);this.quantidade=quantidade;this.precoUnitario=produto.getPreco();
    }
    public BigDecimal getSubtotal() {return precoUnitario.multiply(BigDecimal.valueOf(quantidade));}
}
