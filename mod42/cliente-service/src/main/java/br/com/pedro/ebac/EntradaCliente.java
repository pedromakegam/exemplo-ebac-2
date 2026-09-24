package br.com.pedro.ebac;

import jakarta.validation.constraints.*;
public record EntradaCliente(@NotBlank @Size(max=100) String nome, @NotBlank @Pattern(regexp="[0-9]{11}") String cpf, @NotBlank @Email @Size(max=150) String email) { }
