package br.com.pedro.ebac;

import java.math.BigDecimal;
public record Produto(long id, String codigo, String nome, BigDecimal preco) {
    public Produto {
        if (codigo == null || codigo.isBlank() || nome == null || nome.isBlank()) throw new IllegalArgumentException("Código e nome obrigatórios");
        if (preco == null || preco.signum() < 0 || preco.scale() > 2) throw new IllegalArgumentException("Preço inválido");

    }
}
