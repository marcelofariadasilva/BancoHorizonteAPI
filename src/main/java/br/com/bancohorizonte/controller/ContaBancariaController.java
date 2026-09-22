package br.com.bancohorizonte.controller;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.ContaBancaria;
import br.com.bancohorizonte.entity.Pessoa;
import br.com.bancohorizonte.service.ContaBancariaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController @RequestMapping("/api/contas")
public class ContaBancariaController {
    
    private final ContaBancariaService service;
    
    public ContaBancariaController(ContaBancariaService service) { 
        this.service = service; 
    }


    @PostMapping 
    @Operation(summary = "Criar produto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Produto criado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public ResponseEntity<ContaResponse> abrir(
            @Valid @RequestBody ContaRequest request) { 
        
        // return ResponseEntity.status(HttpStatus.CREATED).body(service.abrir(request)); 
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

    // @GetMapping("tipocontas")
    // public String listar(@RequestParam String param) {
    //     return new String();
    // }
    

    // @GetMapping("/{id}") public ContaResponse buscar(
    //         @PathVariable Long id) { 
    //     return service.buscar(id); 
    // }

    
    // @GetMapping("/pessoa/{pessoaId}") public List<ContaResponse> listar(@PathVariable Long pessoaId) { return service.listarPorPessoa(pessoaId); }
    // @PatchMapping("/{id}/depositos") public ContaResponse depositar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.depositar(id, r); }
    // @PatchMapping("/{id}/saques") public ContaResponse sacar(@PathVariable Long id, @Valid @RequestBody MovimentacaoRequest r) { return service.sacar(id, r); }


}
