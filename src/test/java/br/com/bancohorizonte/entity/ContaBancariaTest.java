package br.com.bancohorizonte.entity;

import br.com.bancohorizonte.exception.RegraNegocioException;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ContaBancariaTest {
    private ContaBancaria conta(BigDecimal saldo, boolean ativa) { return new ContaBancaria("0001", "123456-7", saldo, ativa, new Pessoa("Camila", "55566677788", "camila@example.com"), new TipoConta("CORRENTE")); }
    @Test void depositoAumentaSaldo() { ContaBancaria c = conta(new BigDecimal("100.00"), true); c.depositar(new BigDecimal("50.00")); assertEquals(0, c.getSaldo().compareTo(new BigDecimal("150.00"))); }
    @Test void saqueInsuficienteMantemSaldo() { ContaBancaria c = conta(new BigDecimal("100.00"), true); assertThrows(RegraNegocioException.class, () -> c.sacar(new BigDecimal("101.00"))); assertEquals(0, c.getSaldo().compareTo(new BigDecimal("100.00"))); }
    @Test void contaInativaNaoMovimenta() { assertThrows(RegraNegocioException.class, () -> conta(BigDecimal.ZERO, false).depositar(BigDecimal.ONE)); }
}
