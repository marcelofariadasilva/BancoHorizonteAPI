package br.com.bancohorizonte.exception; public class PessoaNaoEncontradaException extends BusinessException { public PessoaNaoEncontradaException(Long id){super("Pessoa não encontrada: "+id);} }
