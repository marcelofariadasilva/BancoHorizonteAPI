package br.com.bancohorizonte.dto;
import br.com.bancohorizonte.entity.Pessoa;
public record PessoaResponse(Long id, String nome, String cpf, String email) { public static PessoaResponse from(Pessoa p) { return new PessoaResponse(p.getId(), p.getNome(), p.getCpf(), p.getEmail()); } }
