package br.com.pedro.ebac;

public record Cliente(long id, String cpf, String nome) {
    public Cliente {
        if (cpf == null || !cpf.matches("[0-9]{11}")) throw new IllegalArgumentException("CPF inválido");
        if (nome == null || nome.isBlank()) throw new IllegalArgumentException("Nome obrigatório");

    }
}
