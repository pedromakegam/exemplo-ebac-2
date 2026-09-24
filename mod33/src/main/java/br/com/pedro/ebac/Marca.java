package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity @Table(name="marca")
public class Marca {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true) private String nome;
    protected Marca() { }
    public Marca(String valor) { if(valor==null || valor.isBlank()) throw new IllegalArgumentException("Valor obrigatório"); this.nome=valor; }
    public Long getId() { return id; }
    public String getNome() { return nome; }
}
