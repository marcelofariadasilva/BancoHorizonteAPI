package br.com.bancohorizonte.controller;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.entity.Pessoa;
import br.com.bancohorizonte.service.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController 
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService service;

    public PessoaController(PessoaService service) { this.service = service; }
    
    @PostMapping
    @Operation(summary = "Criar pessoa")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Pessoa cadastrada"),
            @ApiResponse(responseCode = "409", description = "Dados inválidos")
    })
    public ResponseEntity<PessoaResponse> cadastrar(@Valid @RequestBody PessoaRequest request) { 
        Pessoa pessoa = service.cadastrar(request.nome(), request.cpf(), request.email());

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pessoa.getId())
            .toUri();

        return ResponseEntity.created(location).body(PessoaResponse.de(pessoa)); 
    }


    @GetMapping("path")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    


}
