package br.com.bancohorizonte.entity;

import java.math.BigDecimal;
import java.time.Instant;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "transacao")
public class Transacao {
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY) 
    @JoinColumn (name = "contas_bancarias_id", nullable = false)
    private ContaBancaria contaBancaria;

    private BigDecimal valor;

    private Instant createdAt;


    public Transacao(BigDecimal valor) {
        this.valor = valor;
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }

    public ContaBancaria getContaBancaria() { return contaBancaria; }

    public Instant getCreatedAt() { return createdAt; }

    public BigDecimal getValor() { return valor; }
}
