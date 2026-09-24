package br.com.pedro.ebac;

import jakarta.persistence.*;import java.time.LocalDate;
@Entity @Table(name="memes") public class Meme {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false) private LocalDate dataCadastro;
@Column(nullable=false,length=100) private String nome;
@Column(nullable=false,length=500) private String descricao;
@Column(nullable=false,length=2048) private String url;
@Column(nullable=false) private Long usuarioId;
@Column(nullable=false) private Long categoriaId;
protected Meme(){}
public Meme(Entrada entrada){dataCadastro=LocalDate.now();this.nome=entrada.nome();this.descricao=entrada.descricao();this.url=entrada.url();this.usuarioId=entrada.usuarioId();this.categoriaId=entrada.categoriaId();}
public Long getId(){return id;}public LocalDate getDataCadastro(){return dataCadastro;}
public String getNome(){return nome;}
public String getDescricao(){return descricao;}
public String getUrl(){return url;}
public Long getUsuarioId(){return usuarioId;}
public Long getCategoriaId(){return categoriaId;}
}
