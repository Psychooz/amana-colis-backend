package com.amana.colisbackend.backend.config;

import com.amana.colisbackend.backend.dto.LoginResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<LoginResponseDTO> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
        LoginResponseDTO response = new LoginResponseDTO(false, "Méthode HTTP non supportée: " + ex.getMethod());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<LoginResponseDTO> handleValidationErrors(MethodArgumentNotValidException ex) {
        StringBuilder errors = new StringBuilder();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.append(error.getField()).append(": ").append(error.getDefaultMessage()).append("; ")
        );

        LoginResponseDTO response = new LoginResponseDTO(false, "Erreurs de validation: " + errors.toString());
        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NoHandlerFoundException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Endpoint non trouvé: " + ex.getRequestURL());
        response.put("method", ex.getHttpMethod());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<LoginResponseDTO> handleGenericException(Exception ex) {
        LoginResponseDTO response = new LoginResponseDTO(false, "Erreur serveur: " + ex.getMessage());
        return ResponseEntity.ok(response);
    }
}