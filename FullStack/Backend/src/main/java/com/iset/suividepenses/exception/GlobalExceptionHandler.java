package com.iset.suividepenses.exception;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> onNotFound(ResourceNotFoundException ex) {
        return jsonError(HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, Object>> onConflict(ConflictException ex) {
        return jsonError(HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Map<String, Object>> onBadRequest(BadRequestException ex) {
        return jsonError(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> onValidationError(MethodArgumentNotValidException ex) {
        Map<String, String> details = new LinkedHashMap<>();
        for (FieldError err : ex.getBindingResult().getFieldErrors()) {
            details.putIfAbsent(err.getField(), err.getDefaultMessage());
        }
        String summary = details.isEmpty()
                ? "Données invalides."
                : details.values().iterator().next();
        return jsonError(HttpStatus.BAD_REQUEST, summary, details);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, Object>> onMalformedPayload(Exception ex) {
        return jsonError(HttpStatus.BAD_REQUEST, "Requête JSON ou paramètre invalide.", null);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> onConstraintViolation(DataIntegrityViolationException ex) {
        return jsonError(HttpStatus.CONFLICT, "Impossible d'effectuer cette opération en raison d'une contrainte en base.", null);
    }

    private ResponseEntity<Map<String, Object>> jsonError(
            HttpStatus status, String message, Map<String, String> errors) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        if (errors != null && !errors.isEmpty()) {
            body.put("errors", errors);
        }
        return ResponseEntity.status(status).body(body);
    }
}
