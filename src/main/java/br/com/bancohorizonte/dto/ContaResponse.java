package br.com.bancohorizonte.dto;
import br.com.bancohorizonte.entity.ContaBancaria;
import java.math.BigDecimal;
public record ContaResponse(Long id, String agencia, String numero, BigDecimal saldo, boolean ativa, Titular titular, Tipo tipoConta) {
    public record Titular(Long id, String nome) { } public record Tipo(Long id, String nome) { }
    public static ContaResponse from(ContaBancaria c) { return new ContaResponse(c.getId(), c.getAgencia(), c.getNumero(), c.getSaldo(), c.isAtiva(), new Titular(c.getTitular().getId(), c.getTitular().getNome()), new Tipo(c.getTipoConta().getId(), c.getTipoConta().getNome())); }
}
