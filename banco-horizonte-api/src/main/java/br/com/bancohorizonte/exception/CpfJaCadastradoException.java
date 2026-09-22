package br.com.bancohorizonte.exception; public class CpfJaCadastradoException extends BusinessException { public CpfJaCadastradoException(String cpf){super("CPF já cadastrado: "+cpf);} }
