package br.com.pedro.ebac;

import jakarta.validation.constraints.*;public record Entrada(@NotBlank @Size(max=100) String nome, @NotBlank @Size(max=500) String descricao, @NotNull @Positive Long usuarioId) {}
