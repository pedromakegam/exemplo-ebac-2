package br.com.pedro.ebac;

public record Cliente(long id, String cpf, String nome, String email) {
    public Cliente {
        if (cpf == null || !cpf.matches("[0-9]{11}")) throw new IllegalArgumentException("CPF inválido");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório");
        if (email == null || email.isBlank() || !email.contains("@")) throw new IllegalArgumentException("Email inválido");
    }
}
