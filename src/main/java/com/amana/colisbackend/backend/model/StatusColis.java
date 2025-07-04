package com.amana.colisbackend.backend.model;

public enum StatusColis {
    EN_TRANSIT("En transit"),
    ECHEC_LIVRAISON_A_RECUPERER("Echec livraison,à récupérer"),
    ENVOI_LIVRE("Envoi livré"),
    ENVOI_RETOURNE("Envoi retourné"),
    DEPOSE("Déposé"),
    DEUXIEME_PRESENTATION("2ème présentation");

    private final String displayName;

    StatusColis(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static StatusColis fromDisplayName(String displayName) {
        if (displayName == null || displayName.trim().isEmpty()) {
            return EN_TRANSIT;
        }

        for (StatusColis status : StatusColis.values()) {
            if (status.getDisplayName().equalsIgnoreCase(displayName.trim())) {
                return status;
            }
        }

        System.err.println("Statut inconnu: '" + displayName + "', utilisation de EN_TRANSIT par défaut");
        return EN_TRANSIT;
    }

    @Override
    public String toString() {
        return displayName;
    }
}