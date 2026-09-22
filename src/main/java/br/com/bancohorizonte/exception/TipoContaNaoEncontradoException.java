package br.com.bancohorizonte.exception;

public class TipoContaNaoEncontradoException extends RuntimeException { 
    public TipoContaNaoEncontradoException(String mensagem) { 
        super(mensagem); 
    }
    
}
