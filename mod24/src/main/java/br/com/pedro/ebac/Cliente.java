package br.com.pedro.ebac;

public record Cliente(String cpf, String nome) {
    public Cliente {
        if (cpf == null || !cpf.matches("[0-9]{11}")) throw new IllegalArgumentException("CPF deve ter 11 dígitos");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        nome = nome.trim();
    }
}
