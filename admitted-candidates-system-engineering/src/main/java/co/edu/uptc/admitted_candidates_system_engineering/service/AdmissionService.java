package co.edu.uptc.admitted_candidates_system_engineering.service;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import co.edu.uptc.admitted_candidates_system_engineering.repository.ICandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio principal para gestionar el proceso de admisión de candidatos.
 * Aplica principio Single Responsibility: coordina el proceso de admisión.
 * Aplica principio Loose Coupling: depende de interfaces, no implementaciones.
 * Aplica principio DRY: centraliza la lógica del proceso de admisión.
 */
@Service
public class AdmissionService implements IAdmissionService {
    
    private final ICandidateRepository candidateRepository;
    private final ISorter<Candidate> candidateSorter;
    private final CandidateDataLoader dataLoader;
    
    @Autowired
    public AdmissionService(ICandidateRepository candidateRepository,
                           ISorter<Candidate> candidateSorter,
                           CandidateDataLoader dataLoader) {
        this.candidateRepository = candidateRepository;
        this.candidateSorter = candidateSorter;
        this.dataLoader = dataLoader;
    }
    
    @Override
    public void initializeSystem() throws Exception {
        try {
            List<Candidate> candidates = dataLoader.loadDefaultCandidates();
            
            for (Candidate candidate : candidates) {
                candidateRepository.addCandidate(candidate);
            }
            
        } catch (Exception e) {
            throw new Exception("Error al inicializar el sistema: " + e.getMessage(), e);
        }
    }
    
    @Override
    public List<Candidate> processAdmittedCandidates() {
        List<Candidate> allCandidates = candidateRepository.getAllCandidates();
        return candidateSorter.sort(allCandidates);
    }
    
    @Override
    public int getTotalCandidates() {
        return candidateRepository.getTotalCandidates();
    }
}
