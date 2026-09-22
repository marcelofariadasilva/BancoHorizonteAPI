package br.com.bancohorizonte.controller;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.service.ContaBancariaService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/contas")
public class ContaBancariaController {
    private final ContaBancariaService service;
    public ContaBancariaController(ContaBancariaService service) { this.service = service; }
    @PostMapping public ResponseEntity<ContaResponse> abrir(@Valid @RequestBody ContaRequest r) { return ResponseEntity.status(HttpStatus.CREATED).body(service.abrir(r)); }
    @GetMapping("/{id}") public ContaResponse buscar(@PathVariable Long id) { return service.buscar(id); }
    @GetMapping("/pessoa/{pessoaId}") public List<ContaResponse> listar(@PathVariable Long pessoaId) { return service.listarPorPessoa(pessoaId); }
    @PatchMapping("/{id}/depositos") public ContaResponse depositar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.depositar(id, r); }
    @PatchMapping("/{id}/saques") public ContaResponse sacar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.sacar(id, r); }
}
