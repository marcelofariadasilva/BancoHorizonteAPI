package br.com.bancohorizonte.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record ContaRequest(@NotBlank @Pattern(regexp = "\\d{4}") String agencia, @NotBlank @Pattern(regexp = "\\d{6}-\\d") String numero, @NotNull @DecimalMin("0.0") BigDecimal saldoInicial, boolean ativa, @NotNull Long titularId, @NotNull Long tipoContaId) { }
