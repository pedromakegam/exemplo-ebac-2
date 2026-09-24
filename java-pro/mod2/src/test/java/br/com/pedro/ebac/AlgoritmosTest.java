package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;
class AlgoritmosTest {
    @Test void calculaFatoriaisBaseEValorConhecido() {
        assertEquals(BigInteger.ONE, Algoritmos.fatorialRecursivo(0));
        assertEquals(BigInteger.ONE, Algoritmos.fatorialTopDown(1));
        assertEquals(new BigInteger("120"), Algoritmos.fatorialRecursivo(5));
        assertEquals(new BigInteger("2432902008176640000"), Algoritmos.fatorialBottomUp(20));
    }
    @Test void versoesConcordamAcimaDeCemSemOverflowPrimitivo() {
        BigInteger resultado = Algoritmos.fatorialBottomUp(101);
        assertEquals(160, resultado.toString().length());
        assertEquals(resultado, Algoritmos.fatorialTopDown(101));
        assertEquals(resultado, Algoritmos.fatorialRecursivo(101));
    }
    @Test void comparaFibonacciComResultadosConhecidos() {
        assertEquals(BigInteger.ZERO, Algoritmos.fibonacciDinamico(0));
        assertEquals(BigInteger.ONE, Algoritmos.fibonacciDinamico(1));
        assertEquals(new BigInteger("55"), Algoritmos.fibonacciDinamico(10));
        for (int n = 0; n <= 20; n++) assertEquals(BigInteger.valueOf(Algoritmos.fibonacciIngenuo(n)), Algoritmos.fibonacciDinamico(n));
        assertEquals(new BigInteger("354224848179261915075"), Algoritmos.fibonacciDinamico(100));
    }
    @Test void rejeitaIndicesNegativos() {
        assertThrows(IllegalArgumentException.class, () -> Algoritmos.fatorialRecursivo(-1));
        assertThrows(IllegalArgumentException.class, () -> Algoritmos.fatorialTopDown(-1));
        assertThrows(IllegalArgumentException.class, () -> Algoritmos.fatorialBottomUp(-1));
        assertThrows(IllegalArgumentException.class, () -> Algoritmos.fibonacciDinamico(-1));
    }
}
