package br.com.bancohorizonte.service;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.Pessoa;
import br.com.bancohorizonte.exception.RegraNegocioException;
import br.com.bancohorizonte.repository.PessoaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessoaService {
    private final PessoaRepository pessoas;
    public PessoaService(PessoaRepository pessoas) { this.pessoas = pessoas; }
    @Transactional public PessoaResponse cadastrar(PessoaRequest r) { if (pessoas.existsByCpf(r.cpf())) throw new RegraNegocioException("CPF já cadastrado."); return PessoaResponse.from(pessoas.save(new Pessoa(r.nome().trim(), r.cpf(), r.email().trim()))); }
}
