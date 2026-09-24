package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.math.BigInteger;
import static org.junit.jupiter.api.Assertions.*;
class FibonacciTest {
    @Test void indiceZero() { assertEquals(BigInteger.ZERO, Fibonacci.calcular(0)); }
    @Test void indiceUm() { assertEquals(BigInteger.ONE, Fibonacci.calcular(1)); }
    @Test void indiceDois() { assertEquals(BigInteger.ONE, Fibonacci.calcular(2)); }
    @Test void indiceDez() { assertEquals(new BigInteger("55"), Fibonacci.calcular(10)); }
    @Test void indiceCem() { assertEquals(new BigInteger("354224848179261915075"), Fibonacci.calcular(100)); }
    @Test void indiceNegativo() { assertThrows(IllegalArgumentException.class, () -> Fibonacci.calcular(-1)); }
}
