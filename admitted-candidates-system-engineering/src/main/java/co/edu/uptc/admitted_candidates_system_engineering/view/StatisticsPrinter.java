package co.edu.uptc.admitted_candidates_system_engineering.view;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import co.edu.uptc.admitted_candidates_system_engineering.service.AdmissionStatistics;
import org.springframework.stereotype.Component;

/**
 * Implementación para mostrar estadísticas del proceso de admisión.
 * Aplica principio KISS: formato simple y claro.
 * Aplica principio Single Responsibility: solo maneja la presentación de estadísticas.
 */
@Component
public class StatisticsPrinter implements IStatisticsPrinter {
    
    @Override
    public void printStatistics(AdmissionStatistics statistics) {
        printHeader();
        printMainStatistics(statistics);
        printTopCandidates(statistics);
        printFooter();
    }
    
    private void printHeader() {
        System.out.println(" ESTADÍSTICAS DEL PROCESO DE ADMISIÓN");
        System.out.println("=".repeat(50));
    }
    
    private void printMainStatistics(AdmissionStatistics statistics) {
        System.out.printf(" Total de candidatos: %d%n", statistics.getTotalCandidates());
        System.out.printf("  Candidatos de comunidades minoritarias: %d (%.1f%%)%n", 
                         statistics.getMinorityCandidates(), 
                         statistics.getMinorityPercentage());
        System.out.printf(" Puntaje ICFES promedio: %.1f%n", statistics.getAverageIcfesScore());
        System.out.printf(" Puntaje ICFES máximo: %d%n", statistics.getMaxIcfesScore());
        System.out.printf(" Puntaje ICFES mínimo: %d%n", statistics.getMinIcfesScore());
    }
    
    private void printTopCandidates(AdmissionStatistics statistics) {
        System.out.println("\n TOP 3 CANDIDATOS:");
        
        for (int i = 0; i < statistics.getTopCandidates().size(); i++) {
            Candidate candidate = statistics.getTopCandidates().get(i);
            String minorityIndicator = candidate.isMinorityCommunity() ? " (Comunidad Minoritaria)" : "";
            System.out.printf("%d. %s %s - ICFES: %d%s%n", 
                             i + 1, 
                             candidate.getName(), 
                             candidate.getLastName(), 
                             candidate.getGlobalIcfesScore(),
                             minorityIndicator);
        }
    }
    
    private void printFooter() {
        System.out.println("\n" + "=".repeat(50));
    }
}
