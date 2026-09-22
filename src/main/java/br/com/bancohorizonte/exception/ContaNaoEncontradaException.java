package br.com.bancohorizonte.exception;

public class ContaNaoEncontradaException extends RuntimeException { 
    public ContaNaoEncontradaException(String mensagem) { 
        super(mensagem); 
    } 
}

