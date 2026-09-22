package br.com.bancohorizonte.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipos_conta", uniqueConstraints = @UniqueConstraint(name = "uk_tipo_nome", columnNames = "nome"))
public class TipoConta {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 30) private String nome;
    protected TipoConta() { }
    public TipoConta(String nome) { this.nome = nome; }
    public Long getId() { return id; } public String getNome() { return nome; }
}
