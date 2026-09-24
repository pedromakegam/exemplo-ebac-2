package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity @Table(name="cliente",schema="mod38")
public class Cliente {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nome;
 @Column(nullable=false,length=150,unique=true) private String email;
 public Cliente() {}
 public Cliente(String nome,String email){this.nome=nome;this.email=email;}
 public Long getId(){return id;} public String getNome(){return nome;} public void setNome(String n){nome=n;}
 public String getEmail(){return email;} public void setEmail(String e){email=e;}
}
