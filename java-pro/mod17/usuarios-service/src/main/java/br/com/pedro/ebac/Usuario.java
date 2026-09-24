package br.com.pedro.ebac;

import jakarta.persistence.*;import java.time.LocalDate;
@Entity @Table(name="usuarios") public class Usuario {
@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
@Column(nullable=false) private LocalDate dataCadastro;
@Column(nullable=false,length=100) private String nome;
@Column(nullable=false,length=150,unique=true) private String email;
protected Usuario(){}
public Usuario(Entrada entrada){dataCadastro=LocalDate.now();this.nome=entrada.nome();this.email=entrada.email();}
public Long getId(){return id;}public LocalDate getDataCadastro(){return dataCadastro;}
public String getNome(){return nome;}
public String getEmail(){return email;}
}
