package br.com.pedro.ebac;

import java.time.LocalDate;
import java.util.Objects;
public final class PessoaFisica extends Pessoa {
    private final String cpf;
    private final LocalDate nascimento;
    public PessoaFisica(String nome, String endereco, String cpf, LocalDate nascimento) {
        super(nome, endereco);
        this.cpf = obrigatorio(cpf, "CPF");
        this.nascimento = Objects.requireNonNull(nascimento);
    }
    @Override public String getDocumento() { return cpf; }
    public LocalDate getNascimento() { return nascimento; }
}
