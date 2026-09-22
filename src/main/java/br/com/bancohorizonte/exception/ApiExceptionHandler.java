package br.com.bancohorizonte.exception;

import java.time.Instant;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(RecursoNaoEncontradoException.class) ResponseEntity<?> naoEncontrado(RuntimeException e) { return erro(HttpStatus.NOT_FOUND, e.getMessage()); }
    @ExceptionHandler(RegraNegocioException.class) ResponseEntity<?> conflito(RuntimeException e) { return erro(HttpStatus.CONFLICT, e.getMessage()); }
    @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> invalido(MethodArgumentNotValidException e) { String m = e.getBindingResult().getFieldErrors().stream().findFirst().map(x -> x.getField() + ": " + x.getDefaultMessage()).orElse("Dados inválidos."); return erro(HttpStatus.BAD_REQUEST, m); }
    private ResponseEntity<?> erro(HttpStatus s, String m) { return ResponseEntity.status(s).body(Map.of("timestamp", Instant.now(), "status", s.value(), "erro", s.getReasonPhrase(), "mensagem", m)); }
}
