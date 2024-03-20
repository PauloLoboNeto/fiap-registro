package com.fiap.authenticacao.application.web.exception;

import com.fiap.authenticacao.application.web.PontoControllerAdapter;
import com.fiap.authenticacao.domain.exception.PontoInvalidoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice(assignableTypes = PontoControllerAdapter.class)
public class PontoExceptionHandler {
    @ExceptionHandler(PontoInvalidoException.class)
    public ResponseEntity<?> pontoInvalido(PontoInvalidoException e, HttpServletRequest request) {
        StandardError err = new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Ponto inválido", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }
}
