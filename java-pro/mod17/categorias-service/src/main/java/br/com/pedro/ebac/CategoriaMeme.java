package br.com.pedro.ebac;

import jakarta.persistence.*;import java.time.LocalDate;
@Entity @Table(name="categorias") public class CategoriaMeme {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false) private LocalDate dataCadastro;
@Column(nullable=false,length=100) private String nome;
@Column(nullable=false,length=500) private String descricao;
@Column(nullable=false) private Long usuarioId;
protected CategoriaMeme(){}
public CategoriaMeme(Entrada entrada){dataCadastro=LocalDate.now();this.nome=entrada.nome();this.descricao=entrada.descricao();this.usuarioId=entrada.usuarioId();}
public Long getId(){return id;}public LocalDate getDataCadastro(){return dataCadastro;}
public String getNome(){return nome;}
public String getDescricao(){return descricao;}
public Long getUsuarioId(){return usuarioId;}
}
