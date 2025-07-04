package com.amana.colisbackend.backend.repository;

import com.amana.colisbackend.backend.model.Colis;
import com.amana.colisbackend.backend.model.StatusColis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ColisRepository extends JpaRepository<Colis, Long> {

    // Find by client
    Page<Colis> findByClientId(Long clientId, Pageable pageable);

    // Find by code
    Optional<Colis> findByCodeEnvoi(String codeEnvoi);

    // Statistics queries
    @Query("SELECT COUNT(c) FROM Colis c WHERE c.client.id = :clientId")
    Long countByClientId(@Param("clientId") Long clientId);

    @Query("SELECT SUM(c.crbt) FROM Colis c WHERE c.client.id = :clientId")
    BigDecimal sumCrbtByClientId(@Param("clientId") Long clientId);

    @Query("SELECT COUNT(c) FROM Colis c WHERE c.client.id = :clientId AND c.dateDepot BETWEEN :startDate AND :endDate")
    Long countByClientIdAndDateDepotBetween(@Param("clientId") Long clientId,
                                            @Param("startDate") LocalDate startDate,
                                            @Param("endDate") LocalDate endDate);

    @Query("SELECT SUM(c.crbt) FROM Colis c WHERE c.client.id = :clientId AND c.dateDepot BETWEEN :startDate AND :endDate")
    BigDecimal sumCrbtByClientIdAndDateDepotBetween(@Param("clientId") Long clientId,
                                                    @Param("startDate") LocalDate startDate,
                                                    @Param("endDate") LocalDate endDate);

    // Status statistics
    @Query("SELECT c.status, COUNT(c) FROM Colis c WHERE c.client.id = :clientId GROUP BY c.status")
    List<Object[]> countByStatusGroupByClientId(@Param("clientId") Long clientId);

    @Query("SELECT c.isPayed, COUNT(c) FROM Colis c WHERE c.client.id = :clientId GROUP BY c.isPayed")
    List<Object[]> countByPaymentStatusGroupByClientId(@Param("clientId") Long clientId);

    // Monthly statistics
    @Query("SELECT YEAR(c.dateDepot), MONTH(c.dateDepot), COUNT(c), SUM(c.crbt) " +
            "FROM Colis c WHERE c.client.id = :clientId " +
            "GROUP BY YEAR(c.dateDepot), MONTH(c.dateDepot) " +
            "ORDER BY YEAR(c.dateDepot), MONTH(c.dateDepot)")
    List<Object[]> getMonthlyStatsByClientId(@Param("clientId") Long clientId);

    // City statistics
    @Query("SELECT c.destination, COUNT(c) FROM Colis c WHERE c.client.id = :clientId GROUP BY c.destination")
    List<Object[]> countByDestinationGroupByClientId(@Param("clientId") Long clientId);

    // Complex filtering query
    @Query("SELECT c FROM Colis c WHERE c.client.id = :clientId " +
            "AND (:codeEnvoi IS NULL OR c.codeEnvoi LIKE %:codeEnvoi%) " +
            "AND (:telDestinataire IS NULL OR c.telDestinataire LIKE %:telDestinataire%) " +
            "AND (:status IS NULL OR c.status = :status) " +
            "AND (:destination IS NULL OR c.destination LIKE %:destination%) " +
            "AND (:isPayed IS NULL OR c.isPayed = :isPayed) " +
            "AND (:dateDepotStart IS NULL OR c.dateDepot >= :dateDepotStart) " +
            "AND (:dateDepotEnd IS NULL OR c.dateDepot <= :dateDepotEnd) " +
            "AND (:dateStatutStart IS NULL OR c.dateStatut >= :dateStatutStart) " +
            "AND (:dateStatutEnd IS NULL OR c.dateStatut <= :dateStatutEnd) " +
            "AND (:datePaiementStart IS NULL OR c.datePaiement >= :datePaiementStart) " +
            "AND (:datePaiementEnd IS NULL OR c.datePaiement <= :datePaiementEnd)")
    Page<Colis> findByClientIdWithFilters(
            @Param("clientId") Long clientId,
            @Param("codeEnvoi") String codeEnvoi,
            @Param("telDestinataire") String telDestinataire,
            @Param("status") StatusColis status,
            @Param("destination") String destination,
            @Param("isPayed") Boolean isPayed,
            @Param("dateDepotStart") LocalDate dateDepotStart,
            @Param("dateDepotEnd") LocalDate dateDepotEnd,
            @Param("dateStatutStart") LocalDate dateStatutStart,
            @Param("dateStatutEnd") LocalDate dateStatutEnd,
            @Param("datePaiementStart") LocalDate datePaiementStart,
            @Param("datePaiementEnd") LocalDate datePaiementEnd,
            Pageable pageable);
}