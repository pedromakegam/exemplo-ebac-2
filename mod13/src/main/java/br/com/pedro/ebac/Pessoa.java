package br.com.pedro.ebac;

import java.util.Objects;
public abstract class Pessoa {
    private final String nome;
    private final String endereco;
    protected Pessoa(String nome, String endereco) {
        this.nome = obrigatorio(nome, "nome");
        this.endereco = obrigatorio(endereco, "endereço");
    }
    protected static String obrigatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) throw new IllegalArgumentException(campo + " obrigatório");
        return valor.trim();
    }
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public abstract String getDocumento();
}
