package co.edu.uptc.admitted_candidates_system_engineering.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa un candidato admitido al programa de Ingeniería de Sistemas.
 * Aplica principio KISS: Clase simple y directa con propósito claro.
 */
public class Candidate {
    private String name;
    private String lastName;
    private String ethnicity;
    private int globalIcfesScore;
    private LocalDate registrationDate;
    private int mathScore;
    private int englishScore;

    // Constructor por defecto
    public Candidate() {}

    // Constructor completo
    public Candidate(String name, String lastName, String ethnicity, 
                    int globalIcfesScore, LocalDate registrationDate, 
                    int mathScore, int englishScore) {
        this.name = name;
        this.lastName = lastName;
        this.ethnicity = ethnicity;
        this.globalIcfesScore = globalIcfesScore;
        this.registrationDate = registrationDate;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    /**
     * Determina si el candidato pertenece a una comunidad minoritaria.
     * Aplica principio KISS: Lógica simple y clara.
     * 
     * @return true si pertenece a etnia indígena o comunidad minoritaria
     */
    public boolean isMinorityCommunity() {
        return ethnicity != null && 
               (ethnicity.toLowerCase().contains("indígena") || 
                ethnicity.toLowerCase().contains("indigena") ||
                ethnicity.toLowerCase().contains("afrocolombiano") ||
                ethnicity.toLowerCase().contains("raizal") ||
                ethnicity.toLowerCase().contains("palenquero") ||
                ethnicity.toLowerCase().contains("rom"));
    }

    // Getters y Setters - Aplicando principio KISS: nombres claros y directos
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public int getGlobalIcfesScore() {
        return globalIcfesScore;
    }

    public void setGlobalIcfesScore(int globalIcfesScore) {
        this.globalIcfesScore = globalIcfesScore;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public int getMathScore() {
        return mathScore;
    }

    public void setMathScore(int mathScore) {
        this.mathScore = mathScore;
    }

    public int getEnglishScore() {
        return englishScore;
    }

    public void setEnglishScore(int englishScore) {
        this.englishScore = englishScore;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Candidate candidate = (Candidate) o;
        return globalIcfesScore == candidate.globalIcfesScore &&
               mathScore == candidate.mathScore &&
               englishScore == candidate.englishScore &&
               Objects.equals(name, candidate.name) &&
               Objects.equals(lastName, candidate.lastName) &&
               Objects.equals(ethnicity, candidate.ethnicity) &&
               Objects.equals(registrationDate, candidate.registrationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, ethnicity, globalIcfesScore, 
                          registrationDate, mathScore, englishScore);
    }

    @Override
    public String toString() {
        return String.format("%s %s - ICFES: %d", name, lastName, globalIcfesScore);
    }
}
