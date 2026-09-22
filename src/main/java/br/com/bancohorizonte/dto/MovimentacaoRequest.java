package br.com.bancohorizonte.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record MovimentacaoRequest(@NotNull @Positive BigDecimal valor) { }
