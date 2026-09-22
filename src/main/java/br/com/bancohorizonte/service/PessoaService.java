package br.com.bancohorizonte.service;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.Pessoa;
import br.com.bancohorizonte.exception.RegraNegocioException;
import br.com.bancohorizonte.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {
    private final PessoaRepository pessoasRepository;

    public PessoaService(PessoaRepository pessoas) { 
        this.pessoasRepository = pessoas; 
    }


    @Transactional public Pessoa cadastrar(String nome, String cpf, String email) {
        
        if (pessoasRepository.existsByCpf(cpf)) {
            throw new RegraNegocioException("CPF já cadastrado");
        }

        Pessoa pessoa = new Pessoa(nome, cpf, email);
        return pessoasRepository.save(pessoa);
    }

    
}
