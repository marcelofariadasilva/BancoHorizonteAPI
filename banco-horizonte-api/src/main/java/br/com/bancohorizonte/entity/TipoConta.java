package br.com.bancohorizonte.entity;
import jakarta.persistence.*;
@Entity @Table(name="tipos_conta") public class TipoConta { @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id; @Column(nullable=false,unique=true,length=30) private String nome; protected TipoConta(){} public TipoConta(String nome){this.nome=nome;} public Long getId(){return id;} public String getNome(){return nome;} }
