package br.com.bancohorizonte.dto; import java.math.BigDecimal; public record AbrirContaRequest(String agencia,String numero,BigDecimal saldoInicial,boolean ativa,Long titularId,Long tipoContaId) {}
