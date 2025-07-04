package com.amana.colisbackend.backend.service;

import com.amana.colisbackend.backend.dto.ClientDTO;
import com.amana.colisbackend.backend.dto.LoginRequestDTO;
import com.amana.colisbackend.backend.dto.LoginResponseDTO;
import com.amana.colisbackend.backend.model.Client;
import com.amana.colisbackend.backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    @Autowired
    private ClientRepository clientRepository;

        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            Optional<Client> clientOpt = clientRepository.findByEmail(loginRequest.getEmail());

            if (clientOpt.isEmpty()) {
                return new LoginResponseDTO(false, "Email not found");
            }

            Client client = clientOpt.get();

            // Check password
            if (!passwordEncoder.matches(loginRequest.getPassword(), client.getPassword())) {
                return new LoginResponseDTO(false, "Invalid password");
            }

            // Convert to DTO
            ClientDTO clientDTO = convertToDTO(client);

            return new LoginResponseDTO(true, "Login successful", clientDTO);
        } catch (Exception e) {
            return new LoginResponseDTO(false, "Login failed: " + e.getMessage());
        }
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(plainPassword);
    }

    private ClientDTO convertToDTO(Client client) {
        return new ClientDTO(
                client.getId(),
                client.getEmail(),
                client.getNom(),
                client.getPrenom(),
                client.getTelephone(),
                client.getProfileType(),
                client.getCreatedAt()
        );
    }
}
