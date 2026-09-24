package br.com.pedro.ebac;

import java.time.LocalDate;
import java.util.List;
public class ExemploPessoas {
    public static void main(String[] args) {
        List<Pessoa> pessoas = List.of(
            new PessoaFisica("Ana Exemplo", "Rua A, 10", "00000000000", LocalDate.of(1995, 5, 20)),
            new PessoaJuridica("Loja Exemplo", "Rua B, 20", "00000000000000", "Loja Exemplo Ltda."));
        pessoas.forEach(p -> System.out.println(p.getNome() + ": " + p.getDocumento()));
    }
}
