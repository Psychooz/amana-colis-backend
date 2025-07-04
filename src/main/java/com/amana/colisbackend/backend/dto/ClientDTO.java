package com.amana.colisbackend.backend.dto;

import java.time.LocalDateTime;

public class ClientDTO {
    private Long id;
    private String email;
    private String nom;
    private String prenom;
    private String telephone;
    private String profileType;
    private LocalDateTime createdAt;

    // Constructors
    public ClientDTO() {}

    public ClientDTO(Long id, String email, String nom, String prenom, String telephone, String profileType, LocalDateTime createdAt) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
        this.profileType = profileType;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getProfileType() {
        return profileType;
    }

    public void setProfileType(String profileType) {
        this.profileType = profileType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}