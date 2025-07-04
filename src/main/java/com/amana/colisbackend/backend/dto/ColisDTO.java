package com.amana.colisbackend.backend.dto;

import com.amana.colisbackend.backend.model.StatusColis;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class ColisDTO {
    private Long id;
    private String codeEnvoi;
    private LocalDate dateDepot;
    private String destination;
    private StatusColis status;
    private LocalDate dateStatut;
    private BigDecimal crbt;
    private BigDecimal poids;
    private String destinataire;
    private String telDestinataire;
    private String adresse;
    private LocalDate datePaiement;
    private String telLivreur;
    private Boolean isPayed;
    private LocalDateTime createdAt;
    private Long clientId;

    // Constructors
    public ColisDTO() {}

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEnvoi() {
        return codeEnvoi;
    }

    public void setCodeEnvoi(String codeEnvoi) {
        this.codeEnvoi = codeEnvoi;
    }

    public LocalDate getDateDepot() {
        return dateDepot;
    }

    public void setDateDepot(LocalDate dateDepot) {
        this.dateDepot = dateDepot;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public StatusColis getStatus() {
        return status;
    }

    public void setStatus(StatusColis status) {
        this.status = status;
    }

    public LocalDate getDateStatut() {
        return dateStatut;
    }

    public void setDateStatut(LocalDate dateStatut) {
        this.dateStatut = dateStatut;
    }

    public BigDecimal getCrbt() {
        return crbt;
    }

    public void setCrbt(BigDecimal crbt) {
        this.crbt = crbt;
    }

    public BigDecimal getPoids() {
        return poids;
    }

    public void setPoids(BigDecimal poids) {
        this.poids = poids;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public String getTelDestinataire() {
        return telDestinataire;
    }

    public void setTelDestinataire(String telDestinataire) {
        this.telDestinataire = telDestinataire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public LocalDate getDatePaiement() {
        return datePaiement;
    }

    public void setDatePaiement(LocalDate datePaiement) {
        this.datePaiement = datePaiement;
    }

    public String getTelLivreur() {
        return telLivreur;
    }

    public void setTelLivreur(String telLivreur) {
        this.telLivreur = telLivreur;
    }

    public Boolean getIsPayed() {
        return isPayed;
    }

    public void setIsPayed(Boolean isPayed) {
        this.isPayed = isPayed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}