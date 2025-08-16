package co.edu.uptc.admitted_candidates_system_engineering.service;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Implementación del ordenamiento de candidatos según reglas de admisión.
 * Aplica principio KISS: Lógica clara y directa para ordenamiento.
 * Aplica principio DRY: Reutiliza Comparator para evitar duplicación.
 * Aplica principio YAGNI: Solo implementa las reglas especificadas.
 */
@Service
public class CandidateSorter implements ISorter<Candidate> {
    
    @Override
    public List<Candidate> sort(List<Candidate> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            return new ArrayList<>();
        }
        
        List<Candidate> sortedCandidates = new ArrayList<>(candidates);
        
        // Comparador que implementa las reglas de ordenamiento
        Comparator<Candidate> candidateComparator = createCandidateComparator();
        
        sortedCandidates.sort(candidateComparator);
        
        return sortedCandidates;
    }
    
    /**
     * Crea el comparador con las reglas de ordenamiento especificadas.
     * Aplica principio DRY: Centraliza la lógica de comparación.
     * 
     * @return comparador configurado con todas las reglas
     */
    private Comparator<Candidate> createCandidateComparator() {
        return Comparator
            // 1. Prioridad para comunidades minoritarias
            .<Candidate>comparingInt(candidate -> candidate.isMinorityCommunity() ? 0 : 1)
            // 2. Orden descendente por resultado global ICFES (mayor a menor)
            .thenComparing(Candidate::getGlobalIcfesScore, Comparator.reverseOrder())
            // 3. En caso de empate, orden descendente por matemáticas
            .thenComparing(Candidate::getMathScore, Comparator.reverseOrder())
            // 4. En caso de empate, orden descendente por inglés
            .thenComparing(Candidate::getEnglishScore, Comparator.reverseOrder())
            // 5. Como último criterio, orden por apellido (alfabético)
            .thenComparing(Candidate::getLastName)
            .thenComparing(Candidate::getName);
    }
}
