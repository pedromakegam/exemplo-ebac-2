package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class CalculadoraTest {
    private final Calculadora calculadora = new Calculadora();
    @Test void adiciona() { assertEquals(5, calculadora.adicionar(2,3)); assertEquals(0.3, calculadora.adicionar(0.1,0.2), 1e-12); }
    @Test void subtrai() { assertEquals(-1, calculadora.subtrair(2,3)); }
    @Test void multiplica() { assertEquals(-6, calculadora.multiplicar(-2,3)); }
    @Test void divide() { assertEquals(2.5, calculadora.dividir(5,2)); }
    @Test void rejeitaDivisaoPorZero() {
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(1,0));
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(1,-0.0));
        assertThrows(ArithmeticException.class, () -> calculadora.dividir(0,0));
    }
}
