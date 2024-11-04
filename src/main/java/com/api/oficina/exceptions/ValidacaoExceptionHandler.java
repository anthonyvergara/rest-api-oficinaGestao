package com.api.oficina.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class ValidacaoExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
        StringBuilder errorMessage = new StringBuilder("Erro de validação: ");
        ex.getConstraintViolations().forEach(violation -> 
            errorMessage.append(violation.getMessage()).append("; ")
        );
        return new ResponseEntity<>(errorMessage.toString(), HttpStatus.BAD_REQUEST);
    }
}