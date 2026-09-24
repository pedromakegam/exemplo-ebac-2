package br.com.pedro.ebac;

import java.util.NoSuchElementException;
public final class ListaEncadeada {
    private static final class No {
        private final int valor;
        private No proximo;
        private No(int valor) { this.valor = valor; }
    }
    private No primeiro;
    private No ultimo;
    private int tamanho;
    public void push(int valor) {
        No novo = new No(valor);
        if (ultimo == null) primeiro = novo;
        else ultimo.proximo = novo;
        ultimo = novo;
        tamanho++;
    }
    public int pop() {
        if (tamanho == 0) throw new NoSuchElementException("Lista vazia");
        return remove(tamanho - 1);
    }
    public void insert(int indice, int valor) {
        if (indice < 0 || indice > tamanho) throw new IndexOutOfBoundsException(indice);
        if (indice == tamanho) { push(valor); return; }
        No novo = new No(valor);
        if (indice == 0) { novo.proximo = primeiro; primeiro = novo; }
        else { No anterior = noEm(indice - 1); novo.proximo = anterior.proximo; anterior.proximo = novo; }
        tamanho++;
    }
    public int remove(int indice) {
        validarIndice(indice);
        No removido;
        if (indice == 0) {
            removido = primeiro;
            primeiro = primeiro.proximo;
            if (tamanho == 1) ultimo = null;
        } else {
            No anterior = noEm(indice - 1);
            removido = anterior.proximo;
            anterior.proximo = removido.proximo;
            if (removido == ultimo) ultimo = anterior;
        }
        tamanho--;
        return removido.valor;
    }
    public int elementAt(int indice) { return noEm(indice).valor; }
    public int size() { return tamanho; }
    public void printList() { System.out.println(this); }
    private void validarIndice(int indice) {
        if (indice < 0 || indice >= tamanho) throw new IndexOutOfBoundsException(indice);
    }
    private No noEm(int indice) {
        validarIndice(indice);
        No atual = primeiro;
        for (int i = 0; i < indice; i++) atual = atual.proximo;
        return atual;
    }
    @Override public String toString() {
        StringBuilder texto = new StringBuilder("[");
        for (No atual = primeiro; atual != null; atual = atual.proximo) {
            if (atual != primeiro) texto.append(", ");
            texto.append(atual.valor);
        }
        return texto.append(']').toString();
    }
}
