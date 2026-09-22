package br.com.bancohorizonte.dto;
import jakarta.validation.constraints.*;
public record PessoaRequest(@NotBlank String nome, @NotBlank @Pattern(regexp = "\\d{11}", message = "CPF deve ter 11 números.") String cpf, @NotBlank @Email String email) { }
