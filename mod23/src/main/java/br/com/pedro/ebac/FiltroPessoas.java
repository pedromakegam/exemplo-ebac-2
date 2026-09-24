package br.com.pedro.ebac;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
public class FiltroPessoas {
    public static List<Pessoa> ler(String entrada) {
        if (entrada == null) throw new IllegalArgumentException("Entrada obrigatória");
        if (entrada.isBlank()) return List.of();
        return Arrays.stream(entrada.split(",", -1)).map(String::trim).map(FiltroPessoas::converter).toList();
    }
    private static Pessoa converter(String item) {
        int separador = item.lastIndexOf('-');
        if (separador <= 0) throw new IllegalArgumentException("Use nome-F ou nome-M: " + item);
        String nome = item.substring(0, separador).trim();
        Pessoa.Genero genero = switch (item.substring(separador + 1).trim().toUpperCase(Locale.ROOT)) {
            case "F", "FEMININO" -> Pessoa.Genero.FEMININO;
            case "M", "MASCULINO" -> Pessoa.Genero.MASCULINO;
            default -> throw new IllegalArgumentException("Gênero inválido: " + item);
        };
        return new Pessoa(nome, genero);
    }
    public static List<Pessoa> mulheres(List<Pessoa> pessoas) {
        return pessoas.stream().filter(p -> p.genero() == Pessoa.Genero.FEMININO).toList();
    }
    public static void main(String[] args) {
        try (Scanner console = new Scanner(System.in)) {
            System.out.println("Informe nome-gênero, separados por vírgulas (ex.: Ana-F, Bruno-M):");
            if (!console.hasNextLine()) return;
            try {
                mulheres(ler(console.nextLine())).forEach(p -> System.out.println(p.nome()));
            } catch (IllegalArgumentException erro) {
                System.err.println(erro.getMessage());
            }
        }
    }
}
