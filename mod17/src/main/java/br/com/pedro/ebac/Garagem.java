package br.com.pedro.ebac;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
public class Garagem<T extends Carro> {
    private final List<T> carros = new ArrayList<>();
    public void adicionar(T carro) { carros.add(Objects.requireNonNull(carro)); }
    public List<T> listar() { return List.copyOf(carros); }
}
