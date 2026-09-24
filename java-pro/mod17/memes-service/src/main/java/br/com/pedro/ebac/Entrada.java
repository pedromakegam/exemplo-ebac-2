package br.com.pedro.ebac;

import jakarta.validation.constraints.*;public record Entrada(@NotBlank @Size(max=100) String nome, @NotBlank @Size(max=500) String descricao, @NotBlank @Size(max=2048) String url, @NotNull @Positive Long usuarioId, @NotNull @Positive Long categoriaId) {}
