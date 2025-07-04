package com.amana.colisbackend.backend.service;

import com.amana.colisbackend.backend.dto.ColisDTO;
import com.amana.colisbackend.backend.dto.StatisticsDTO;
import com.amana.colisbackend.backend.model.Colis;
import com.amana.colisbackend.backend.model.StatusColis;
import com.amana.colisbackend.backend.repository.ColisRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ColisService {

    @Autowired
    private ColisRepository colisRepository;

    public Page<ColisDTO> getColisByClientId(Long clientId, int page, int size, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);
        Page<Colis> colisPage = colisRepository.findByClientId(clientId, pageable);

        return colisPage.map(this::convertToDTO);
    }

    public Page<ColisDTO> getColisWithFilters(
            Long clientId,
            String codeEnvoi,
            String telDestinataire,
            StatusColis status,
            String destination,
            Boolean isPayed,
            LocalDate dateDepotStart,
            LocalDate dateDepotEnd,
            LocalDate dateStatutStart,
            LocalDate dateStatutEnd,
            LocalDate datePaiementStart,
            LocalDate datePaiementEnd,
            int page,
            int size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Colis> colisPage = colisRepository.findByClientIdWithFilters(
                clientId, codeEnvoi, telDestinataire, status, destination, isPayed,
                dateDepotStart, dateDepotEnd, dateStatutStart, dateStatutEnd,
                datePaiementStart, datePaiementEnd, pageable
        );

        return colisPage.map(this::convertToDTO);
    }

    public StatisticsDTO getStatistics(Long clientId, LocalDate startDate, LocalDate endDate) {
        StatisticsDTO stats = new StatisticsDTO();

        // Total colis and CRBT
        Long totalColis = colisRepository.countByClientId(clientId);
        BigDecimal totalCrbt = colisRepository.sumCrbtByClientId(clientId);

        stats.setTotalColis(totalColis != null ? totalColis : 0L);
        stats.setTotalCrbt(totalCrbt != null ? totalCrbt : BigDecimal.ZERO);

        // Period statistics
        if (startDate != null && endDate != null) {
            Long totalEnvoisPeriode = colisRepository.countByClientIdAndDateDepotBetween(clientId, startDate, endDate);
            BigDecimal totalCrbtPeriode = colisRepository.sumCrbtByClientIdAndDateDepotBetween(clientId, startDate, endDate);

            stats.setTotalEnvoisPeriode(totalEnvoisPeriode != null ? totalEnvoisPeriode : 0L);
            stats.setTotalCrbtPeriode(totalCrbtPeriode != null ? totalCrbtPeriode : BigDecimal.ZERO);
        } else {
            stats.setTotalEnvoisPeriode(totalColis);
            stats.setTotalCrbtPeriode(totalCrbt);
        }

        // Status statistics
        List<Object[]> statusResults = colisRepository.countByStatusGroupByClientId(clientId);
        Map<String, Long> statusStats = new HashMap<>();
        for (Object[] result : statusResults) {
            StatusColis status = (StatusColis) result[0];
            Long count = (Long) result[1];
            statusStats.put(status.getDisplayName(), count);
        }
        stats.setStatusStats(statusStats);

        // Payment statistics
        List<Object[]> paymentResults = colisRepository.countByPaymentStatusGroupByClientId(clientId);
        Map<String, Long> paymentStats = new HashMap<>();
        for (Object[] result : paymentResults) {
            Boolean isPayed = (Boolean) result[0];
            Long count = (Long) result[1];
            paymentStats.put(isPayed ? "Payé" : "Impayé", count);
        }
        stats.setPaymentStats(paymentStats);

        // Monthly statistics
        List<Object[]> monthlyResults = colisRepository.getMonthlyStatsByClientId(clientId);
        List<StatisticsDTO.MonthlyStatsDTO> monthlyStats = monthlyResults.stream()
                .map(result -> new StatisticsDTO.MonthlyStatsDTO(
                        (Integer) result[0], // year
                        (Integer) result[1], // month
                        (Long) result[2],    // count
                        (BigDecimal) result[3] // sum
                ))
                .collect(Collectors.toList());
        stats.setMonthlyStats(monthlyStats);

        // City statistics
        List<Object[]> cityResults = colisRepository.countByDestinationGroupByClientId(clientId);
        Map<String, Long> cityStats = new HashMap<>();
        for (Object[] result : cityResults) {
            String destination = (String) result[0];
            Long count = (Long) result[1];
            cityStats.put(destination, count);
        }
        stats.setCityStats(cityStats);

        return stats;
    }

    private ColisDTO convertToDTO(Colis colis) {
        ColisDTO dto = new ColisDTO();
        dto.setId(colis.getId());
        dto.setCodeEnvoi(colis.getCodeEnvoi());
        dto.setDateDepot(colis.getDateDepot());
        dto.setDestination(colis.getDestination());
        dto.setStatus(colis.getStatus());
        dto.setDateStatut(colis.getDateStatut());
        dto.setCrbt(colis.getCrbt());
        dto.setPoids(colis.getPoids());
        dto.setDestinataire(colis.getDestinataire());
        dto.setTelDestinataire(colis.getTelDestinataire());
        dto.setAdresse(colis.getAdresse());
        dto.setDatePaiement(colis.getDatePaiement());
        dto.setTelLivreur(colis.getTelLivreur());
        dto.setIsPayed(colis.getIsPayed());
        dto.setCreatedAt(colis.getCreatedAt());
        dto.setClientId(colis.getClient().getId());
        return dto;
    }
}