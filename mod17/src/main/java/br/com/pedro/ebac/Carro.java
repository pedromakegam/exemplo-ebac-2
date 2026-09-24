package br.com.pedro.ebac;

public abstract class Carro {
    private final String modelo;
    protected Carro(String modelo) {
        if (modelo == null || modelo.isBlank()) throw new IllegalArgumentException("Modelo obrigatório");
        this.modelo = modelo;
    }
    public String getModelo() { return modelo; }
    public abstract String marca();
}
