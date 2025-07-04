package com.amana.colisbackend.backend.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "colis")
public class Colis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_envoi", unique = true)
    private String codeEnvoi;

    @Column(name = "date_depot")
    private LocalDate dateDepot;

    private String destination;

    @Enumerated(EnumType.STRING)
    private StatusColis status;

    @Column(name = "date_statut")
    private LocalDate dateStatut;

    @Column(precision = 10, scale = 2)
    private BigDecimal crbt;

    @Column(precision = 8, scale = 3)
    private BigDecimal poids;

    private String destinataire;

    @Column(name = "tel_destinataire")
    private String telDestinataire;

    private String adresse;

    @Column(name = "date_paiement")
    private LocalDate datePaiement;

    @Column(name = "tel_livreur")
    private String telLivreur;

    @Column(name = "is_payed")
    private Boolean isPayed = false;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (dateDepot == null) {
            dateDepot = LocalDate.now();
        }
        if (dateStatut == null) {
            dateStatut = LocalDate.now();
        }
    }

    // Constructors
    public Colis() {}

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}