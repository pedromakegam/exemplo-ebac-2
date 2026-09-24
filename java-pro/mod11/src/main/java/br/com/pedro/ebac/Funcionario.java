package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity public class Funcionario {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nome;
 protected Funcionario(){} public Funcionario(String nome){this.nome=nome;}
 public Long getId(){return id;}public String getNome(){return nome;}
}
