package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity public class Raca {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nome;
 @Enumerated(EnumType.STRING) @Column(nullable=false,length=20) private Especie especie;
 protected Raca(){} public Raca(String nome,Especie especie){this.nome=nome;this.especie=especie;}
 public Long getId(){return id;}public String getNome(){return nome;}public Especie getEspecie(){return especie;}
}
