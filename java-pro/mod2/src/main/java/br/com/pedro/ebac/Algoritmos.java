package br.com.pedro.ebac;

import java.math.BigInteger;
public final class Algoritmos {
    private Algoritmos() { }
    private static void validar(int n) {
        if (n < 0) throw new IllegalArgumentException("n deve ser não negativo");
    }
    public static BigInteger fatorialRecursivo(int n) {
        validar(n);
        return n <= 1 ? BigInteger.ONE : BigInteger.valueOf(n).multiply(fatorialRecursivo(n - 1));
    }
    public static BigInteger fatorialTopDown(int n) {
        validar(n);
        BigInteger[] memoria = new BigInteger[n + 1];
        memoria[0] = BigInteger.ONE;
        return fatorial(n, memoria);
    }
    private static BigInteger fatorial(int n, BigInteger[] memoria) {
        if (memoria[n] == null) memoria[n] = BigInteger.valueOf(n).multiply(fatorial(n - 1, memoria));
        return memoria[n];
    }
    public static BigInteger fatorialBottomUp(int n) {
        validar(n);
        BigInteger resultado = BigInteger.ONE;
        for (int i = 2; i <= n; i++) resultado = resultado.multiply(BigInteger.valueOf(i));
        return resultado;
    }
    public static long fibonacciIngenuo(int n) {
        validar(n);
        if (n > 40) throw new IllegalArgumentException("Exemplo ingênuo limitado a 40 para evitar execução excessiva");
        return n <= 1 ? n : fibonacciIngenuo(n - 1) + fibonacciIngenuo(n - 2);
    }
    public static BigInteger fibonacciDinamico(int n) {
        validar(n);
        BigInteger anterior = BigInteger.ZERO;
        BigInteger atual = BigInteger.ONE;
        for (int i = 0; i < n; i++) {
            BigInteger proximo = anterior.add(atual);
            anterior = atual;
            atual = proximo;
        }
        return anterior;
    }
    public static void main(String[] args) {
        System.out.println("5! = " + fatorialRecursivo(5));
        System.out.println("101! = " + fatorialBottomUp(101));
        System.out.println("F(100) = " + fibonacciDinamico(100));
    }
}
