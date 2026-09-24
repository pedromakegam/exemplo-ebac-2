package br.com.pedro.ebac;

public class ExemploTabela {
    public static String nomeTabela(Class<?> classe) {
        Tabela tabela = classe.getAnnotation(Tabela.class);
        if (tabela == null || tabela.nome().isBlank()) {
            throw new IllegalArgumentException("Classe sem nome de tabela: " + classe.getSimpleName());
        }
        return tabela.nome();
    }
    public static void main(String[] args) { System.out.println(nomeTabela(Cliente.class)); }
}
