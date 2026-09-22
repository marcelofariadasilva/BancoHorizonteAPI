package br.com.bancohorizonte.controller;

import br.com.bancohorizonte.dto.*;
import br.com.bancohorizonte.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/pessoas")
public class PessoaController {
    private final PessoaService service;
    public PessoaController(PessoaService service) { this.service = service; }
    @PostMapping public ResponseEntity<PessoaResponse> cadastrar(@Valid @RequestBody PessoaRequest r) { return ResponseEntity.status(HttpStatus.CREATED).body(service.cadastrar(r)); }
}
