package br.com.pedro.ebac;

import jakarta.persistence.*;import java.time.LocalDate;
@Entity public class Animal {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @Column(nullable=false,length=100) private String nome;
 @ManyToOne(optional=false) private Raca raca;
 @ManyToOne(optional=false) private Funcionario funcionario;
 @Column(nullable=false) private LocalDate dataResgate;
 protected Animal(){} public Animal(String nome,Raca raca,Funcionario funcionario,LocalDate dataResgate){this.nome=nome;this.raca=raca;this.funcionario=funcionario;this.dataResgate=dataResgate;}
 public Long getId(){return id;}public String getNome(){return nome;}public Raca getRaca(){return raca;}public Funcionario getFuncionario(){return funcionario;}public LocalDate getDataResgate(){return dataResgate;}
}
