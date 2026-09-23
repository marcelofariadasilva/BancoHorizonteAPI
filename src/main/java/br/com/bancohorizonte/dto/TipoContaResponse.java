package br.com.bancohorizonte.dto;

import br.com.bancohorizonte.entity.TipoConta;
import io.swagger.v3.oas.annotations.media.Schema;

public record TipoContaResponse(
    @Schema(description = "ID da conta") Long id,
    @Schema(description = "Nome da conta") String nome
) {

    public static TipoContaResponse de(TipoConta tipoConta) { 
        return new TipoContaResponse(tipoConta.getId(), tipoConta.getNome()); 
    }
    
}
