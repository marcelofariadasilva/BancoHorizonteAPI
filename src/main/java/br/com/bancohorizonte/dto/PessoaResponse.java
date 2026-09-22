package br.com.bancohorizonte.dto;
import br.com.bancohorizonte.entity.Pessoa;

import io.swagger.v3.oas.annotations.media.Schema;

public record PessoaResponse(
    @Schema(description = "ID da pessoa") Long id,
    @Schema(description = "Nome da pessoa") String nome,
    @Schema(description = "CPF da pessoa") String cpf,
    @Schema(description = "Email da pessoa") String email) { 
    
    public static PessoaResponse de(Pessoa pessoa) { 
        return new PessoaResponse(pessoa.getId(), pessoa.getNome(), pessoa.getCpf(), pessoa.getEmail()); 
    } 
}
