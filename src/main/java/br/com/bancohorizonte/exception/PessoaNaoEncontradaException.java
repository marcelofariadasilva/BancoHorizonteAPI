package br.com.bancohorizonte.exception;

public class PessoaNaoEncontradaException extends RuntimeException { 
    public PessoaNaoEncontradaException(String mensagem) { 
        super(mensagem); 
    } 
}

