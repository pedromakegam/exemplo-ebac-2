package br.com.pedro.ebac;

import java.math.BigDecimal;
import jakarta.persistence.*;
        @Entity
        @Table(name="produto")
        public class Produto {
            @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
            @Column(nullable=false,length=100) private String nome;
    @Column(nullable=false,unique=true,length=20) private String codigo;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal preco;
            protected Produto() { }
            public Produto(EntradaProduto entrada) {alterar(entrada);}
            public void alterar(EntradaProduto entrada) {this.nome=entrada.nome();this.codigo=entrada.codigo();this.preco=entrada.preco();}
            public Long getId() {return id;}
        public String getNome() {return nome;}
public String getCodigo() {return codigo;}
public BigDecimal getPreco() {return preco;}
}
