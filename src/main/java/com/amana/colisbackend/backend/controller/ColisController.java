package com.amana.colisbackend.backend.controller;

import com.amana.colisbackend.backend.dto.ColisDTO;
import com.amana.colisbackend.backend.dto.StatisticsDTO;
import com.amana.colisbackend.backend.model.StatusColis;
import com.amana.colisbackend.backend.service.ColisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/colis")
@CrossOrigin(origins = "*")
public class ColisController {

    @Autowired
    private ColisService colisService;

    @GetMapping("/client/{clientId}")
    public ResponseEntity<Page<ColisDTO>> getColisByClient(
            @PathVariable Long clientId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateDepot") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Page<ColisDTO> colis = colisService.getColisByClientId(clientId, page, size, sortBy, sortDir);
        return ResponseEntity.ok(colis);
    }

    @GetMapping("/client/{clientId}/filtered")
    public ResponseEntity<Page<ColisDTO>> getColisWithFilters(
            @PathVariable Long clientId,
            @RequestParam(required = false) String codeEnvoi,
            @RequestParam(required = false) String telDestinataire,
            @RequestParam(required = false) StatusColis status,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) Boolean isPayed,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepotStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepotEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStatutStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStatutEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiementStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiementEnd,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "dateDepot") String sortBy,
            @RequestParam(defaultValue = "desc") String sortDir) {

        Page<ColisDTO> colis = colisService.getColisWithFilters(
                clientId, codeEnvoi, telDestinataire, status, destination, isPayed,
                dateDepotStart, dateDepotEnd, dateStatutStart, dateStatutEnd,
                datePaiementStart, datePaiementEnd, page, size, sortBy, sortDir
        );

        return ResponseEntity.ok(colis);
    }

    @GetMapping("/client/{clientId}/statistics")
    public ResponseEntity<StatisticsDTO> getStatistics(
            @PathVariable Long clientId,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        StatisticsDTO statistics = colisService.getStatistics(clientId, startDate, endDate);
        return ResponseEntity.ok(statistics);
    }


    @GetMapping("/client/{clientId}/statistics/filtered")
    public ResponseEntity<StatisticsDTO> getFilteredStatistics(
            @PathVariable Long clientId,
            @RequestParam(required = false) String codeEnvoi,
            @RequestParam(required = false) String telDestinataire,
            @RequestParam(required = false) StatusColis status,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) Boolean isPayed,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepotStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDepotEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStatutStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateStatutEnd,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiementStart,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate datePaiementEnd) {

        StatisticsDTO statistics = colisService.getFilteredStatistics(
                clientId, codeEnvoi, telDestinataire, status, destination, isPayed,
                dateDepotStart, dateDepotEnd, dateStatutStart, dateStatutEnd,
                datePaiementStart, datePaiementEnd
        );
        return ResponseEntity.ok(statistics);
    }

    @GetMapping("/status-values")
    public ResponseEntity<StatusColis[]> getStatusValues() {
        return ResponseEntity.ok(StatusColis.values());
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Colis API is working!");
    }

}