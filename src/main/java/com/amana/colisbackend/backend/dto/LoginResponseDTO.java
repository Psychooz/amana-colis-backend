package com.amana.colisbackend.backend.dto;

public class LoginResponseDTO {
    private boolean success;
    private String message;
    private ClientDTO client;

    // Constructors
    public LoginResponseDTO() {}

    public LoginResponseDTO(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public LoginResponseDTO(boolean success, String message, ClientDTO client) {
        this.success = success;
        this.message = message;
        this.client = client;
    }

    // Getters and Setters
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ClientDTO getClient() {
        return client;
    }

    public void setClient(ClientDTO client) {
        this.client = client;
    }
}