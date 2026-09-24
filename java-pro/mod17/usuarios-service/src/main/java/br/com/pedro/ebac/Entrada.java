package br.com.pedro.ebac;

import jakarta.validation.constraints.*;public record Entrada(@NotBlank @Size(max=100) String nome, @NotBlank @Email @Size(max=150) String email) {}
