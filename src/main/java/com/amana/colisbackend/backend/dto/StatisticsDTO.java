package com.amana.colisbackend.backend.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class StatisticsDTO {
    private Long totalColis;
    private BigDecimal totalCrbt;
    private Long totalEnvoisPeriode;
    private BigDecimal totalCrbtPeriode;
    private Map<String, Long> statusStats;
    private Map<String, Long> paymentStats;
    private List<MonthlyStatsDTO> monthlyStats;
    private Map<String, Long> cityStats;

    // Constructors
    public StatisticsDTO() {}

    // Getters and Setters
    public Long getTotalColis() {
        return totalColis;
    }

    public void setTotalColis(Long totalColis) {
        this.totalColis = totalColis;
    }

    public BigDecimal getTotalCrbt() {
        return totalCrbt;
    }

    public void setTotalCrbt(BigDecimal totalCrbt) {
        this.totalCrbt = totalCrbt;
    }

    public Long getTotalEnvoisPeriode() {
        return totalEnvoisPeriode;
    }

    public void setTotalEnvoisPeriode(Long totalEnvoisPeriode) {
        this.totalEnvoisPeriode = totalEnvoisPeriode;
    }

    public BigDecimal getTotalCrbtPeriode() {
        return totalCrbtPeriode;
    }

    public void setTotalCrbtPeriode(BigDecimal totalCrbtPeriode) {
        this.totalCrbtPeriode = totalCrbtPeriode;
    }

    public Map<String, Long> getStatusStats() {
        return statusStats;
    }

    public void setStatusStats(Map<String, Long> statusStats) {
        this.statusStats = statusStats;
    }

    public Map<String, Long> getPaymentStats() {
        return paymentStats;
    }

    public void setPaymentStats(Map<String, Long> paymentStats) {
        this.paymentStats = paymentStats;
    }

    public List<MonthlyStatsDTO> getMonthlyStats() {
        return monthlyStats;
    }

    public void setMonthlyStats(List<MonthlyStatsDTO> monthlyStats) {
        this.monthlyStats = monthlyStats;
    }

    public Map<String, Long> getCityStats() {
        return cityStats;
    }

    public void setCityStats(Map<String, Long> cityStats) {
        this.cityStats = cityStats;
    }

    public static class MonthlyStatsDTO {
        private int year;
        private int month;
        private Long totalColis;
        private BigDecimal totalCrbt;

        public MonthlyStatsDTO() {}

        public MonthlyStatsDTO(int year, int month, Long totalColis, BigDecimal totalCrbt) {
            this.year = year;
            this.month = month;
            this.totalColis = totalColis;
            this.totalCrbt = totalCrbt;
        }
        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public Long getTotalColis() {
            return totalColis;
        }

        public void setTotalColis(Long totalColis) {
            this.totalColis = totalColis;
        }

        public BigDecimal getTotalCrbt() {
            return totalCrbt;
        }

        public void setTotalCrbt(BigDecimal totalCrbt) {
            this.totalCrbt = totalCrbt;
        }
    }
}