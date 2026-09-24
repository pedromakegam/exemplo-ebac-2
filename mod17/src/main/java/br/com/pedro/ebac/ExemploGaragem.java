package br.com.pedro.ebac;

public class ExemploGaragem {
    public static void main(String[] args) {
        Garagem<Carro> garagem = new Garagem<>();
        garagem.adicionar(new Honda("Civic"));
        garagem.adicionar(new Toyota("Corolla"));
        garagem.listar().forEach(c -> System.out.println(c.marca() + " " + c.getModelo()));
    }
}
