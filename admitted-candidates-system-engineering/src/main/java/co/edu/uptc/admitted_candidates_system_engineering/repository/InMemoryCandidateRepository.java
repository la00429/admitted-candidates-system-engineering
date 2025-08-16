package co.edu.uptc.admitted_candidates_system_engineering.repository;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación en memoria del repositorio de candidatos.
 * Aplica principio DRY: Evita duplicación usando ArrayList estándar.
 * Aplica principio YAGNI: Solo implementa lo necesario para el caso de uso.
 * Aplica principio Loose Coupling: Implementa la interfaz sin depender de clases específicas.
 */
@Repository
public class InMemoryCandidateRepository implements ICandidateRepository {
    
    private final List<Candidate> candidates;
    
    public InMemoryCandidateRepository() {
        this.candidates = new ArrayList<>();
    }
    
    @Override
    public void addCandidate(Candidate candidate) {
        if (candidate != null) {
            candidates.add(candidate);
        }
    }
    
    @Override
    public List<Candidate> getAllCandidates() {
        // Retorna una nueva lista para evitar modificaciones externas
        return new ArrayList<>(candidates);
    }
    
    @Override
    public int getTotalCandidates() {
        return candidates.size();
    }
}
