package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;
import static org.junit.jupiter.api.Assertions.*;
class EstruturasTest {
    @Test void pilhaCresceEPreservaOrdemLifo() {
        Pilha pilha = new Pilha();
        assertTrue(pilha.isEmpty());
        for (int i = 0; i < 100; i++) pilha.push(i);
        assertEquals(100, pilha.size()); assertEquals(99, pilha.top());
        for (int i = 99; i >= 0; i--) assertEquals(i, pilha.pop());
        assertTrue(pilha.isEmpty());
        assertThrows(NoSuchElementException.class, pilha::pop);
        assertThrows(NoSuchElementException.class, pilha::top);
    }
    @Test void filaPreservaOrdemFifoEPodeSerReutilizada() {
        Fila fila = new Fila();
        fila.enqueue(1); fila.enqueue(2);
        assertEquals(1, fila.front()); assertEquals(2, fila.rear()); assertEquals(2, fila.size());
        assertEquals(1, fila.dequeue()); fila.enqueue(3);
        assertEquals(2, fila.dequeue()); assertEquals(3, fila.dequeue());
        assertTrue(fila.isEmpty());
        assertThrows(NoSuchElementException.class, fila::front);
        assertThrows(NoSuchElementException.class, fila::rear);
        assertThrows(NoSuchElementException.class, fila::dequeue);
        fila.enqueue(-5); assertEquals(-5, fila.dequeue());
    }
    @Test void listaInsereERemoveNoInicioMeioEFim() {
        ListaEncadeada lista = new ListaEncadeada();
        lista.push(2); lista.insert(0, 1); lista.insert(2, 4); lista.insert(2, 3);
        assertEquals("[1, 2, 3, 4]", lista.toString());
        assertEquals(3, lista.elementAt(2)); assertEquals(4, lista.size());
        assertEquals(1, lista.remove(0)); assertEquals(3, lista.remove(1));
        assertEquals(4, lista.pop()); assertEquals(2, lista.pop());
        assertEquals(0, lista.size()); assertEquals("[]", lista.toString());
        lista.push(9); assertEquals(9, lista.pop());
    }
    @Test void listaValidaLimites() {
        ListaEncadeada lista = new ListaEncadeada();
        assertThrows(NoSuchElementException.class, lista::pop);
        assertThrows(IndexOutOfBoundsException.class, () -> lista.insert(1, 10));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.elementAt(0));
        assertThrows(IndexOutOfBoundsException.class, () -> lista.remove(-1));
    }
}
