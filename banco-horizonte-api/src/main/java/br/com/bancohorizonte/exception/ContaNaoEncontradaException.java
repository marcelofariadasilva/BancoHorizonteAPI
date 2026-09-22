package br.com.bancohorizonte.exception; public class ContaNaoEncontradaException extends BusinessException { public ContaNaoEncontradaException(Long id){super("Conta não encontrada: "+id);} }
