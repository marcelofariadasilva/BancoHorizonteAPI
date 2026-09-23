package br.com.bancohorizonte.controller;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.ContaBancaria;
import br.com.bancohorizonte.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;



@RestController @RequestMapping("/api/contas")
public class ContaBancariaController {
    
    private final ContaBancariaService service;
    
    public ContaBancariaController(ContaBancariaService service) { 
        this.service = service; 
    }


    @PostMapping 
    @Operation(summary = "Cadastrar conta bancária")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Conta cadastrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ContaResponse> abrir(
            @Valid @RequestBody ContaRequest request) { 
        
        ContaBancaria conta = service.cadastrar(
            request.agencia(), request.numero(), request.saldoInicial(), 
            request.ativa(), request.titularId(), request.tipoContaId());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(conta.getId())
            .toUri();

        return ResponseEntity.created(location)
            .body(ContaResponse.de(conta, conta.getTitular().getNome(), conta.getTipoConta().getNome())); 
    }

    @GetMapping("/{contasId}")
    @Operation (summary = "Busca conta por ID")
    @ApiResponses ({
            @ApiResponse(responseCode = "200", description = "Conta encontrada"),
            @ApiResponse(responseCode = "404", description = "Conta não encontrada")
    })
    public ContaResponse listar(
            @Parameter (description = "ID da conta", example = "1")
            @PathVariable Long contasId) {

        ContaBancaria conta = service.buscar(contasId);
        
        return ContaResponse.de(conta, conta.getTitular().getNome(), conta.getTipoConta().getNome());
    }

    @GetMapping("/tipoconta")
    @Operation(summary = "Listar tipos de contas")
    @ApiResponse (responseCode = "200", description = "Tipos de contas listados")
    public List<TipoContaResponse> listarTipoContas() {
        return service.listarTipoContas().stream().map(TipoContaResponse::de).toList();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public List<ContaResponse> listarPorPessoa(
        @Parameter(description = "ID da pessoa", example = "1")
        @PathVariable Long pessoaId) {

        return service.listarPorPessoa(pessoaId).stream()
                .map(conta -> ContaResponse
                    .de(conta, conta.getTitular().getNome(), conta.getTipoConta().getNome()))
                .toList();
    }
    
    
    

    // @GetMapping("/{id}") public ContaResponse buscar(
    //         @PathVariable Long id) { 
    //     return service.buscar(id); 
    // }

    
    // @GetMapping("/pessoa/{pessoaId}") public List<ContaResponse> listar(@PathVariable Long pessoaId) { return service.listarPorPessoa(pessoaId); }
    // @PatchMapping("/{id}/depositos") public ContaResponse depositar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.depositar(id, r); }
    // @PatchMapping("/{id}/saques") public ContaResponse sacar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.sacar(id, r); }


}