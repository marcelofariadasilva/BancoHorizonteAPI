package br.com.bancohorizonte.entity;

import br.com.bancohorizonte.exception.RegraNegocioException;
import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contas_bancarias", uniqueConstraints = @UniqueConstraint(name = "uk_conta_agencia_numero", columnNames = {"agencia", "numero"}))
public class ContaBancaria {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    
    @Column(nullable = false, length = 4) private String agencia;
    
    @Column(nullable = false, length = 8) private String numero;
    
    @Column(nullable = false, precision = 19, scale = 2) private BigDecimal saldo;
    
    @Column(nullable = false) private boolean ativa;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    @JoinColumn(name = "titular_id", nullable = false) 
    private Pessoa titular;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    @JoinColumn(name = "tipo_conta_id", nullable = false) 
    private TipoConta tipoConta;
    
    protected ContaBancaria() { }
    
    public ContaBancaria(String agencia, String numero, BigDecimal saldo, boolean ativa, Pessoa titular, TipoConta tipoConta) { 
        this.agencia = agencia; 
        this.numero = numero; 
        this.saldo = saldo; 
        this.ativa = ativa; 
        this.titular = titular; 
        this.tipoConta = tipoConta; 
    }
    
    public void depositar(BigDecimal valor) { 
        validarMovimentacao(valor); 
        saldo = saldo.add(valor); 
    }

    
    public void sacar(BigDecimal valor) { 
        validarMovimentacao(valor); 
        if (saldo.compareTo(valor) < 0) 
            throw new RegraNegocioException("Saldo insuficiente."); 
        
        saldo = saldo.subtract(valor); 
    }
    
    private void validarMovimentacao(BigDecimal valor) { 
        if (!ativa) throw new RegraNegocioException("Conta inativa."); 
        if (valor == null || valor.signum() <= 0) throw new RegraNegocioException("O valor deve ser maior que zero."); 
    }
    
    public Long getId() { 
        return id; 
    } 
    
    public String getAgencia() { 
        return agencia; 
    } 
    
    public String getNumero() { 
        return numero; 
    }
    
    public BigDecimal getSaldo() { return saldo; } public boolean isAtiva() { return ativa; } public Pessoa getTitular() { return titular; } public TipoConta getTipoConta() { return tipoConta; }
}
