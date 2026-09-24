package br.com.pedro.ebac;

public record Pessoa(String nome, Genero genero) {
    public Pessoa {
        if (nome == null || nome.isBlank() || genero == null) throw new IllegalArgumentException("Pessoa inválida");
        nome = nome.trim();
    }
    public enum Genero { FEMININO, MASCULINO }
}
