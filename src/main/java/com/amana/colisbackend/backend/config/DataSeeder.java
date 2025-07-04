package com.amana.colisbackend.backend.config;

import com.amana.colisbackend.backend.model.Client;
import com.amana.colisbackend.backend.model.Colis;
import com.amana.colisbackend.backend.model.StatusColis;
import com.amana.colisbackend.backend.repository.ClientRepository;
import com.amana.colisbackend.backend.repository.ColisRepository;
import com.amana.colisbackend.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

//@Component
public class DataSeeder implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ColisRepository colisRepository;

    @Autowired
    private AuthService authService;

    @Override
    public void run(String... args) throws Exception {
        // Only seed if database is empty
        if (clientRepository.count() == 0) {
            seedData();
        }
    }

    private void seedData() {
        // Create test client
        Client testClient = new Client();
        testClient.setEmail("test@amana.com");
        testClient.setPassword(authService.hashPassword("password123"));
        testClient.setNom("Test");
        testClient.setPrenom("User");
        testClient.setTelephone("0612345678");
        testClient.setProfileType("Client");

        testClient = clientRepository.save(testClient);

        // Create sample colis data
        Random random = new Random();
        String[] destinations = {"Casablanca", "Rabat", "Fes", "Marrakech", "Tanger", "Agadir", "Oujda", "Kenitra", "Tetouan", "Sale"};
        StatusColis[] statuses = StatusColis.values();

        for (int i = 1; i <= 50; i++) {
            Colis colis = new Colis();
            colis.setCodeEnvoi("QL" + String.format("%09d", 1000000000L + i) + "MA");
            colis.setDateDepot(LocalDate.now().minusDays(random.nextInt(365)));
            colis.setDestination(destinations[random.nextInt(destinations.length)]);
            colis.setStatus(statuses[random.nextInt(statuses.length)]);
            colis.setDateStatut(colis.getDateDepot().plusDays(random.nextInt(10)));
            colis.setCrbt(new BigDecimal(50 + random.nextInt(500))); // CRBT between 50 and 550
            colis.setDestinataire("Destinataire " + i);
            colis.setTelDestinataire("06" + String.format("%08d", random.nextInt(100000000)));
            colis.setAdresse("Adresse " + i + ", " + colis.getDestination());

            // Random payment status
            if (random.nextBoolean()) {
                colis.setIsPayed(true);
                colis.setDatePaiement(colis.getDateStatut().plusDays(random.nextInt(5)));
            } else {
                colis.setIsPayed(false);
            }

            colis.setTelLivreur("06" + String.format("%08d", random.nextInt(100000000)));
            colis.setClient(testClient);

            colisRepository.save(colis);
        }

        System.out.println("Sample data seeded successfully!");
        System.out.println("Test login: test@amana.com / password123");
    }
}