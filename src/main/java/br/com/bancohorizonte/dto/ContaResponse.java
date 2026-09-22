package br.com.bancohorizonte.dto;

import br.com.bancohorizonte.entity.ContaBancaria;
import java.math.BigDecimal;

public record ContaResponse(
    Long id, 
    String agencia, 
    String numero, 
    BigDecimal saldo, 
    boolean ativa, 
    String titularNome, 
    String tipoConta) {

    public record Titular(Long id, String nome) { } 
    
    public record Tipo(Long id, String nome) { }

    public static ContaResponse de(ContaBancaria contaBancaria, String titular, String tipoConta) { 
        return new ContaResponse(contaBancaria.getId(), 
            contaBancaria.getAgencia(), 
            contaBancaria.getNumero(), 
            contaBancaria.getSaldo(), 
            contaBancaria.isAtiva(), 
            titular, 
            tipoConta
        ); 
        
    }
}
