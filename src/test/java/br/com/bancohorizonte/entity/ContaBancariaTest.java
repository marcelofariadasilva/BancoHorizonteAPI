package br.com.bancohorizonte.entity;

import br.com.bancohorizonte.exception.RegraNegocioException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ContaBancariaTest {

    private ContaBancaria conta(BigDecimal saldo, boolean ativa) {
        return new ContaBancaria(
                "0001",
                "123456-7",
                saldo,
                ativa,
                new Pessoa("Camila", "55566677788", "camila@example.com"),
                new TipoConta("CORRENTE")
        );
    }

    @Test
    void depositoAumentaSaldo() {
        ContaBancaria conta = conta(new BigDecimal("100.00"), true);

        conta.depositar(new BigDecimal("50.00"));

        assertEquals(
                0,
                conta.getSaldo().compareTo(new BigDecimal("150.00"))
        );
    }

    @Test
    void saqueInsuficienteMantemSaldo() {
        ContaBancaria conta = conta(new BigDecimal("100.00"), true);

        assertThrows(
                RegraNegocioException.class,
                () -> conta.sacar(new BigDecimal("101.00"))
        );

        assertEquals(
                0,
                conta.getSaldo().compareTo(new BigDecimal("100.00"))
        );
    }

    @Test
    void contaInativaNaoMovimenta() {
        ContaBancaria conta = conta(BigDecimal.ZERO, false);

        assertThrows(
                RegraNegocioException.class,
                () -> conta.depositar(BigDecimal.ONE)
        );
    }

    @Test
    void valorInvalidoNaoMovimenta() {
        ContaBancaria conta = conta(new BigDecimal("100.00"), true);

        assertThrows(
                RegraNegocioException.class,
                () -> conta.depositar(BigDecimal.ZERO)
        );

        assertEquals(
                0,
                conta.getSaldo().compareTo(new BigDecimal("100.00"))
        );
    }
}