package br.com.pedro.ebac;

import java.util.NoSuchElementException;
public final class Pilha {
    private int[] valores = new int[8];
    private int tamanho;
    public void push(int valor) {
        if (tamanho == valores.length) {
            int[] ampliado = new int[valores.length * 2];
            System.arraycopy(valores, 0, ampliado, 0, tamanho);
            valores = ampliado;
        }
        valores[tamanho++] = valor;
    }
    public int pop() {
        if (isEmpty()) throw new NoSuchElementException("Pilha vazia");
        return valores[--tamanho];
    }
    public int top() {
        if (isEmpty()) throw new NoSuchElementException("Pilha vazia");
        return valores[tamanho - 1];
    }
    public boolean isEmpty() { return tamanho == 0; }
    public int size() { return tamanho; }
}
