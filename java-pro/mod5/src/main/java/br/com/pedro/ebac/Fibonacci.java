package br.com.pedro.ebac;

import java.math.BigInteger;
/** Sequência com F(0)=0 e F(1)=1, calculada de forma iterativa. */
public final class Fibonacci {
    private Fibonacci() { }
    /**
     * Calcula o termo de índice n sem overflow de tipos primitivos.
     * @param n índice não negativo
     * @return termo F(n)
     * @throws IllegalArgumentException para um índice negativo
     */
    public static BigInteger calcular(int n) {
        if (n < 0) throw new IllegalArgumentException("Índice negativo");
        BigInteger anterior = BigInteger.ZERO, atual = BigInteger.ONE;
        for (int i=0; i<n; i++) {
            BigInteger proximo = anterior.add(atual);
            anterior = atual;
            atual = proximo;
        }
        return anterior;
    }
}
