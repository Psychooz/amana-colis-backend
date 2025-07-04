package com.amana.colisbackend.backend.controller;

import com.amana.colisbackend.backend.dto.LoginRequestDTO;
import com.amana.colisbackend.backend.dto.LoginResponseDTO;
import com.amana.colisbackend.backend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@Valid @RequestBody LoginRequestDTO loginRequest) {
        try {
            LoginResponseDTO response = authService.login(loginRequest);

            if (response.isSuccess()) {
                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.ok(response); // 200 - success=false
            }
        }catch (Exception e) {
            LoginResponseDTO errorResponse = new LoginResponseDTO(false, "Erreur serveur: " + e.getMessage());
            return ResponseEntity.ok(errorResponse);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth API is working!");
    }
    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.PUT, RequestMethod.DELETE})
    public ResponseEntity<LoginResponseDTO> handleWrongMethod() {
        LoginResponseDTO response = new LoginResponseDTO(false, "Méthode non autorisée. Utilisez POST.");
        return ResponseEntity.ok(response);
    }
}