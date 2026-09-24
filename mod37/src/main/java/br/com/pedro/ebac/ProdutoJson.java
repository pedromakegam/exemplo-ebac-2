package br.com.pedro.ebac;

import com.google.gson.Gson;
import java.math.BigDecimal;
public final class ProdutoJson {
    public record Produto(String codigo, String nome, BigDecimal preco) { }
    private final Gson gson = new Gson();
    public String escrever(Produto produto) { return gson.toJson(produto); }
    public Produto ler(String json) { return gson.fromJson(json, Produto.class); }
}
