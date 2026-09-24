package br.com.pedro.ebac;

import java.util.NoSuchElementException;
public final class Fila {
    private static final class No {
        private final int valor;
        private No proximo;
        private No(int valor) { this.valor = valor; }
    }
    private No primeiro;
    private No ultimo;
    private int tamanho;
    public void enqueue(int valor) {
        No novo = new No(valor);
        if (ultimo == null) primeiro = novo;
        else ultimo.proximo = novo;
        ultimo = novo;
        tamanho++;
    }
    public int dequeue() {
        int valor = front();
        primeiro = primeiro.proximo;
        if (--tamanho == 0) ultimo = null;
        return valor;
    }
    public int front() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        return primeiro.valor;
    }
    public int rear() {
        if (isEmpty()) throw new NoSuchElementException("Fila vazia");
        return ultimo.valor;
    }
    public boolean isEmpty() { return tamanho == 0; }
    public int size() { return tamanho; }
}
