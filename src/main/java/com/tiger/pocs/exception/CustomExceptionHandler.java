package com.tiger.pocs.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;

@ControllerAdvice
public class CustomExceptionHandler extends RuntimeException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomBadRequest> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex) {
        var errors = new HashMap<String, String>();
        ex.getBindingResult().getFieldErrors()
                .forEach(violation -> errors.put(violation.getField(), violation.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                new CustomBadRequest("Request Validation failed", HttpStatus.BAD_REQUEST, errors)
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> handleResourceNotFoundException(
            ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND)
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomError> handleGlobalException(
            Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new CustomError(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR)
        );
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CustomError> handleAccessDeniedException(
            AccessDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new CustomError(ex.getMessage(), HttpStatus.FORBIDDEN)
        );
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<CustomError> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
                new CustomError(ex.getMessage(), HttpStatus.METHOD_NOT_ALLOWED)
        );
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<CustomError> handleConflictException(
            ConflictException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                new CustomError(ex.getMessage(), HttpStatus.CONFLICT)
        );
    }
}