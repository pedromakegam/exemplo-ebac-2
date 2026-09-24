package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity @Table(name="acessorio")
public class Acessorio {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true) private String descricao;
    protected Acessorio() { }
    public Acessorio(String valor) { if(valor==null || valor.isBlank()) throw new IllegalArgumentException("Valor obrigatório"); this.descricao=valor; }
    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
}
