package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class GaragemTest {
    @Test void guardaSubclassesSemPerderOPolimorfismo() {
        Garagem<Carro> garagem = new Garagem<>();
        garagem.adicionar(new Honda("Civic")); garagem.adicionar(new Toyota("Corolla"));
        assertEquals(2, garagem.listar().size());
        assertEquals("Honda", garagem.listar().get(0).marca());
        assertEquals("Toyota", garagem.listar().get(1).marca());
        assertThrows(UnsupportedOperationException.class, () -> garagem.listar().clear());
    }
}
