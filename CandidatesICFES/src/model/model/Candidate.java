package model;

import java.time.LocalDate;

public class Candidate {

    private String firstName;
    private String lastName;
    private String ethnicity;
    private double icfesScore;
    private LocalDate registrationDate;
    private double mathScore;
    private double englishScore;

    public Candidate(String firstName, String lastName, String ethnicity,
                    double icfesScore, LocalDate registrationDate,
                    double mathScore, double englishScore) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.ethnicity = ethnicity;
        this.icfesScore = icfesScore;
        this.registrationDate = registrationDate;
        this.mathScore = mathScore;
        this.englishScore = englishScore;
    }

    // === Getters ===
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public double getIcfesScore() {
        return icfesScore;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public double getMathScore() {
        return mathScore;
    }

    public double getEnglishScore() {
        return englishScore;
    }

    // === Setters ===
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity;
    }

    public void setIcfesScore(double icfesScore) {
        this.icfesScore = icfesScore;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setMathScore(double mathScore) {
        this.mathScore = mathScore;
    }

    public void setEnglishScore(double englishScore) {
        this.englishScore = englishScore;
    }

    public boolean isMinorityCommunity() {
    if (ethnicity == null) return false;
    String e = ethnicity.trim().toLowerCase();
    return e.contains("indigena") ||
        e.contains("gitano") ||
        e.contains("judio") ||
        e.contains("afro") ||
        e.contains("raizales");
    }
}
