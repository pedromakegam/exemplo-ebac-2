package br.com.pedro.ebac;

import java.math.BigDecimal;
import jakarta.validation.constraints.*;
public record EntradaProduto(@NotBlank @Size(max=100) String nome, @NotBlank @Size(max=20) String codigo, @NotNull @DecimalMin("0.00") @Digits(integer=10,fraction=2) BigDecimal preco) { }
