package br.com.bancohorizonte.exception;

import br.com.bancohorizonte.dto.ApiErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    ResponseEntity<ApiErrorResponse> naoEncontrado(
            RecursoNaoEncontradoException exception,
            HttpServletRequest request
    ) {
        return erro(HttpStatus.NOT_FOUND, exception.getMessage(), request);
    }

    @ExceptionHandler(RegraNegocioException.class)
    ResponseEntity<ApiErrorResponse> conflito(
            RegraNegocioException exception,
            HttpServletRequest request
    ) {
        return erro(HttpStatus.CONFLICT, exception.getMessage(), request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiErrorResponse> dadosInvalidos(
            MethodArgumentNotValidException exception,
            HttpServletRequest request
    ) {
        String mensagem = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .findFirst()
                .map(erro -> erro.getField() + ": " + erro.getDefaultMessage())
                .orElse("Dados inválidos.");

        return erro(HttpStatus.BAD_REQUEST, mensagem, request);
    }

    private ResponseEntity<ApiErrorResponse> erro(
            HttpStatus status,
            String mensagem,
            HttpServletRequest request
    ) {
        ApiErrorResponse resposta = new ApiErrorResponse(
                Instant.now(),
                status.value(),
                status.getReasonPhrase(),
                mensagem,
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(resposta);
    }
}