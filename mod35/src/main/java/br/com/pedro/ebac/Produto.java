package br.com.pedro.ebac;

import jakarta.persistence.*;
import java.math.BigDecimal;
@Entity @Table(name="produto")
public class Produto {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=20) private String codigo;
    @Column(nullable=false,length=100) private String nome;
    @Column(nullable=false,precision=12,scale=2) private BigDecimal preco;
    @Column(nullable=false,length=60) private String categoria;
    @Version private long versao;
    protected Produto() { }
    public Produto(String codigo,String nome,BigDecimal preco,String categoria) {
        if(codigo==null || codigo.isBlank() || nome==null || nome.isBlank() || categoria==null || categoria.isBlank()) throw new IllegalArgumentException("Campos obrigatórios");
        this.codigo=codigo; this.nome=nome; this.categoria=categoria; alterarPreco(preco);
    }
    public void alterarPreco(BigDecimal preco) {
        if(preco==null || preco.signum()<0 || preco.scale()>2) throw new IllegalArgumentException("Preço inválido");
        this.preco=preco;
    }
    public Long getId() { return id; }
    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public BigDecimal getPreco() { return preco; }
    public String getCategoria() { return categoria; }
}
