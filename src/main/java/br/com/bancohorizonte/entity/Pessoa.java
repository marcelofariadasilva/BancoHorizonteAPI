package br.com.bancohorizonte.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "pessoas", uniqueConstraints = @UniqueConstraint(name = "uk_pessoa_cpf", columnNames = "cpf"))
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 120) private String nome;
    @Column(nullable = false, length = 11) private String cpf;
    @Column(nullable = false, length = 160) private String email;

    protected Pessoa() { }

    public Pessoa(String nome, String cpf, String email) { 
        this.nome = nome; 
        this.cpf = cpf; 
        this.email = email; 
    }

    public Long getId() { 
        return id; 
    } 
    
    public String getNome() { 
        return nome; 
    }

    public String getCpf() { 
        return cpf; 
    } 
    
    public String getEmail() { 
        return email; 
    }
    
}
