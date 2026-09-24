package br.com.pedro.ebac;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
@Entity @Table(name="carro")
public class Carro {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(nullable=false) private String modelo;
    @ManyToOne(fetch=FetchType.LAZY,optional=false) @JoinColumn(name="marca_id",nullable=false) private Marca marca;
    @ManyToMany @JoinTable(name="carro_acessorio",joinColumns=@JoinColumn(name="carro_id"),inverseJoinColumns=@JoinColumn(name="acessorio_id"))
    private Set<Acessorio> acessorios=new HashSet<>();
    protected Carro() { }
    public Carro(String modelo,Marca marca) {
        if(modelo==null || modelo.isBlank()) throw new IllegalArgumentException("Modelo obrigatório");
        this.modelo=modelo;this.marca=Objects.requireNonNull(marca);
    }
    public void adicionar(Acessorio acessorio) { acessorios.add(Objects.requireNonNull(acessorio)); }
    public Long getId() { return id; }
    public Marca getMarca() { return marca; }
    public Set<Acessorio> getAcessorios() { return Set.copyOf(acessorios); }
}
