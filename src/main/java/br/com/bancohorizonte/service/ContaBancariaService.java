package br.com.bancohorizonte.service;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.*;
import br.com.bancohorizonte.exception.*;
import br.com.bancohorizonte.repository.*;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService {
    private final ContaBancariaRepository contasRepository; 
    private final PessoaRepository pessoasRepository; 
    private final TipoContaRepository tipoContaRepository;
    
    public ContaBancariaService(ContaBancariaRepository contas, PessoaRepository pessoas, TipoContaRepository tipos) { 
        this.contasRepository = contas; 
        this.pessoasRepository = pessoas; 
        this.tipoContaRepository = tipos; 
    }
    
    @Transactional public ContaBancaria cadastrar(
        String agencia, String numero, BigDecimal saldoInicial, boolean ativa, 
        Long titularId, Long tipoContaId
        ) { 

        if (contasRepository.existsByAgenciaAndNumero(agencia, numero)) 
            throw new RegraNegocioException("Agência e número já cadastrados."); 

        if (saldoInicial.compareTo(BigDecimal.ZERO) < 0) 
            throw new RegraNegocioException("Saldo inicial não pode ser negativo.");
        
        Pessoa pessoa = pessoasRepository.findById(titularId)
            .orElseThrow(() -> new PessoaNaoEncontradaException("Pessoa não encontrada."));
        TipoConta tipoConta = tipoContaRepository.findById(tipoContaId)
            .orElseThrow(() -> new TipoContaNaoEncontradoException("Tipo de conta não encontrado.")); 


        ContaBancaria contaBancaria = new ContaBancaria(agencia, numero, saldoInicial, ativa, pessoa, tipoConta);
        
        return contasRepository.save(contaBancaria);
    }


    
    // @Transactional(readOnly = true) public ContaResponse buscar(Long id) { return ContaResponse.from(contasRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada."))); }
    
    // @Transactional(readOnly = true) public List<ContaResponse> listarPorPessoa(Long id) { if (!pessoasRepository.existsById(id)) throw new PessoaNaoEncontradaException("Pessoa não encontrada."); return contasRepository.findByTitularId(id).stream().map(ContaResponse::from).toList(); }
    
    // @Transactional public ContaResponse depositar(Long id, MovimentacaoRequest r) { ContaBancaria contaBancaria = contasRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada.")); contaBancaria.depositar(r.valor()); return ContaResponse.from(contaBancaria); }
    
    // @Transactional public ContaResponse sacar(Long id, MovimentacaoRequest r) { ContaBancaria contaBancaria = contasRepository.findById(id).orElseThrow(() -> new ContaNaoEncontradaException("Conta não encontrada.")); contaBancaria.sacar(r.valor()); return ContaResponse.from(contaBancaria); }
}
