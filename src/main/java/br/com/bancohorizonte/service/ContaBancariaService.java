package br.com.bancohorizonte.service;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.*;
import br.com.bancohorizonte.exception.*;
import br.com.bancohorizonte.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContaBancariaService {
    private final ContaBancariaRepository contas; private final PessoaRepository pessoas; private final TipoContaRepository tipos;
    public ContaBancariaService(ContaBancariaRepository contas, PessoaRepository pessoas, TipoContaRepository tipos) { this.contas = contas; this.pessoas = pessoas; this.tipos = tipos; }
    @Transactional public ContaResponse abrir(ContaRequest r) { if (contas.existsByAgenciaAndNumero(r.agencia(), r.numero())) throw new RegraNegocioException("Agência e número já cadastrados."); Pessoa p = pessoas.findById(r.titularId()).orElseThrow(() -> new RecursoNaoEncontradoException("Pessoa não encontrada.")); TipoConta t = tipos.findById(r.tipoContaId()).orElseThrow(() -> new RecursoNaoEncontradoException("Tipo de conta não encontrado.")); return ContaResponse.from(contas.save(new ContaBancaria(r.agencia(), r.numero(), r.saldoInicial(), r.ativa(), p, t))); }
    @Transactional(readOnly = true) public ContaResponse buscar(Long id) { return ContaResponse.from(contas.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada."))); }
    @Transactional(readOnly = true) public List<ContaResponse> listarPorPessoa(Long id) { if (!pessoas.existsById(id)) throw new RecursoNaoEncontradoException("Pessoa não encontrada."); return contas.findByTitularId(id).stream().map(ContaResponse::from).toList(); }
    @Transactional public ContaResponse depositar(Long id, MovimentacaoRequest r) { ContaBancaria c = contas.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada.")); c.depositar(r.valor()); return ContaResponse.from(c); }
    @Transactional public ContaResponse sacar(Long id, MovimentacaoRequest r) { ContaBancaria c = contas.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Conta não encontrada.")); c.sacar(r.valor()); return ContaResponse.from(c); }
}
