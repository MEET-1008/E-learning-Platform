package com.codewithmeet.elearningplatform.exceptions;

import com.codewithmeet.elearningplatform.dtos.CustomMessage;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomMessage> handleResourceNotFound(ResourceNotFoundException ex) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(ex.getMessage());
        customMessage.setSuccess(false);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(customMessage);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationError(MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);

        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
    }

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<CustomMessage> handleSQLIntegrityConstraintViolation(SQLIntegrityConstraintViolationException ex) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(ex.getMessage());
        customMessage.setSuccess(false);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customMessage);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<CustomMessage> handleConstraintViolation(AuthorizationDeniedException ex) {
        CustomMessage customMessage = new CustomMessage();
        customMessage.setMessage(ex.getMessage());
        customMessage.setSuccess(false);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(customMessage);
    }

}
