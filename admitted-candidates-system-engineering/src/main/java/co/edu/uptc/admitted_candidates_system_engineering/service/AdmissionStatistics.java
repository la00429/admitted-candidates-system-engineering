package co.edu.uptc.admitted_candidates_system_engineering.service;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import java.util.List;

/**
 * Clase que encapsula las estadísticas del proceso de admisión.
 * Aplica principio KISS: Estructura simple para datos estadísticos.
 * Aplica principio Single Responsibility: solo maneja estadísticas.
 */
public class AdmissionStatistics {
    
    private final int totalCandidates;
    private final long minorityCandidates;
    private final double minorityPercentage;
    private final double averageIcfesScore;
    private final int maxIcfesScore;
    private final int minIcfesScore;
    private final List<Candidate> topCandidates;
    
    public AdmissionStatistics(List<Candidate> candidates) {
        this.totalCandidates = candidates.size();
        this.minorityCandidates = calculateMinorityCandidates(candidates);
        this.minorityPercentage = (minorityCandidates * 100.0) / totalCandidates;
        this.averageIcfesScore = calculateAverageIcfes(candidates);
        this.maxIcfesScore = calculateMaxIcfes(candidates);
        this.minIcfesScore = calculateMinIcfes(candidates);
        this.topCandidates = getTopCandidates(candidates, 3);
    }
    
    private long calculateMinorityCandidates(List<Candidate> candidates) {
        return candidates.stream()
            .mapToLong(candidate -> candidate.isMinorityCommunity() ? 1 : 0)
            .sum();
    }
    
    private double calculateAverageIcfes(List<Candidate> candidates) {
        return candidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .average()
            .orElse(0.0);
    }
    
    private int calculateMaxIcfes(List<Candidate> candidates) {
        return candidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .max()
            .orElse(0);
    }
    
    private int calculateMinIcfes(List<Candidate> candidates) {
        return candidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .min()
            .orElse(0);
    }
    
    private List<Candidate> getTopCandidates(List<Candidate> candidates, int count) {
        return candidates.stream()
            .limit(count)
            .toList();
    }
    
    // Getters
    public int getTotalCandidates() {
        return totalCandidates;
    }
    
    public long getMinorityCandidates() {
        return minorityCandidates;
    }
    
    public double getMinorityPercentage() {
        return minorityPercentage;
    }
    
    public double getAverageIcfesScore() {
        return averageIcfesScore;
    }
    
    public int getMaxIcfesScore() {
        return maxIcfesScore;
    }
    
    public int getMinIcfesScore() {
        return minIcfesScore;
    }
    
    public List<Candidate> getTopCandidates() {
        return topCandidates;
    }
}
