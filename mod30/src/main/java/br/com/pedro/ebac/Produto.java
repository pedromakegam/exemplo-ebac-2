package br.com.pedro.ebac;

import java.math.BigDecimal;
public record Produto(long id, String codigo, String nome, BigDecimal preco, String categoria) {
    public Produto {
        if (codigo == null || codigo.isBlank() || nome == null || nome.isBlank()) throw new IllegalArgumentException("Código e nome obrigatórios");
        if (preco == null || preco.signum() < 0 || preco.scale() > 2) throw new IllegalArgumentException("Preço inválido");
        if (categoria == null || categoria.isBlank()) throw new IllegalArgumentException("Categoria obrigatória");
    }
}
