package br.com.pedro.ebac;

import jakarta.persistence.*;
        @Entity
        @Table(name="cliente")
        public class Cliente {
            @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
            @Column(nullable=false,length=100) private String nome;
    @Column(nullable=false,unique=true,length=11) private String cpf;
    @Column(nullable=false,length=150) private String email;
            protected Cliente() { }
            public Cliente(EntradaCliente entrada) {alterar(entrada);}
            public void alterar(EntradaCliente entrada) {this.nome=entrada.nome();this.cpf=entrada.cpf();this.email=entrada.email();}
            public Long getId() {return id;}
        public String getNome() {return nome;}
public String getCpf() {return cpf;}
public String getEmail() {return email;}
}
