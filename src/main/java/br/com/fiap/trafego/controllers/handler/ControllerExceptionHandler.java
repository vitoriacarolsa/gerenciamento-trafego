package br.com.fiap.trafego.controllers.handler;

import br.com.fiap.trafego.dto.CustomError;
import br.com.fiap.trafego.dto.ValidationError;
import br.com.fiap.trafego.services.exceptions.DatabaseException;
import br.com.fiap.trafego.services.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler (ResourceNotFoundException.class)
    public ResponseEntity <CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        HttpStatus status= HttpStatus.NOT_FOUND;
        CustomError err= new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler (DatabaseException.class)
    public ResponseEntity <CustomError> database(DatabaseException e, HttpServletRequest request) {
        HttpStatus status= HttpStatus.BAD_REQUEST;
        CustomError err= new CustomError(Instant.now(), status.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler (MethodArgumentNotValidException.class)
    public ResponseEntity <CustomError> methodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest request) {
        HttpStatus status= HttpStatus.UNPROCESSABLE_ENTITY;
        ValidationError err= new  ValidationError(Instant.now(), status.value(), "Dados inválidos", request.getRequestURI());
       for (FieldError f: e.getBindingResult().getFieldErrors()){
           err.addError(f.getField(), f.getDefaultMessage() );
       }
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException) {
            Map<String, String> error = new HashMap<>();
            error.put("message", "formato invalido");
            return ResponseEntity.badRequest().body(error);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
