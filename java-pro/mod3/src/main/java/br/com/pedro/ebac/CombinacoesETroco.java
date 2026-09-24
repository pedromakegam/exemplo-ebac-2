package br.com.pedro.ebac;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
public final class CombinacoesETroco {
    private CombinacoesETroco() { }
    public static List<List<Integer>> subconjuntos(int[] conjunto, int k) {
        if (conjunto == null || k < 0 || k > conjunto.length) throw new IllegalArgumentException("Conjunto ou tamanho inválido");
        if (Arrays.stream(conjunto).distinct().count() != conjunto.length) throw new IllegalArgumentException("O conjunto não deve repetir valores");
        List<List<Integer>> resultado = new ArrayList<>();
        buscar(conjunto, k, 0, new ArrayList<>(), resultado);
        return List.copyOf(resultado);
    }
    private static void buscar(int[] conjunto, int k, int inicio, List<Integer> atual, List<List<Integer>> resultado) {
        if (atual.size() == k) { resultado.add(List.copyOf(atual)); return; }
        for (int i = inicio; i <= conjunto.length - (k - atual.size()); i++) {
            atual.add(conjunto[i]);
            buscar(conjunto, k, i + 1, atual, resultado);
            atual.remove(atual.size() - 1);
        }
    }
    public static List<Integer> troco(int valor) {
        if (valor < 0) throw new IllegalArgumentException("Valor negativo");
        List<Integer> moedas = new ArrayList<>();
        for (int moeda : new int[]{5, 2, 1}) {
            while (valor >= moeda) { moedas.add(moeda); valor -= moeda; }
        }
        return List.copyOf(moedas);
    }
    public static void main(String[] args) {
        System.out.println(subconjuntos(new int[]{1,2,3}, 2));
        System.out.println("Troco de 18: " + troco(18));
    }
}
