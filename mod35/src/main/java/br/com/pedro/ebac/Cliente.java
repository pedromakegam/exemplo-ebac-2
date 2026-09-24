package br.com.pedro.ebac;

import jakarta.persistence.*;
@Entity @Table(name="cliente")
public class Cliente {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false,unique=true,length=11) private String cpf;
    @Column(nullable=false) private String nome;
    @Column(nullable=false) private String email;
    protected Cliente() { }
    public Cliente(String cpf,String nome,String email) {
        if(cpf==null || !cpf.matches("[0-9]{11}") || nome==null || nome.isBlank() || email==null || !email.contains("@")) throw new IllegalArgumentException("Cliente inválido");
        this.cpf=cpf;this.nome=nome;this.email=email;
    }
    public Long getId() {return id;}
    public String getNome() {return nome;}
    public String getEmail() {return email;}
    public void alterarEmail(String email) {if(email==null || !email.contains("@")) throw new IllegalArgumentException("Email inválido");this.email=email;}
}
