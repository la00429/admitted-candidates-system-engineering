package co.edu.uptc.admitted_candidates_system_engineering.repository;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import java.util.List;

/**
 * Interfaz para el repositorio de candidatos.
 * Aplica principio "Program to Interfaces, not Implementations": 
 * Define contrato sin implementación específica.
 * Aplica principio Loose Coupling: Permite intercambiar implementaciones.
 */
public interface ICandidateRepository {
    
    /**
     * Añade un candidato al repositorio.
     * 
     * @param candidate el candidato a añadir
     */
    void addCandidate(Candidate candidate);
    
    /**
     * Obtiene todos los candidatos del repositorio.
     * 
     * @return lista de todos los candidatos
     */
    List<Candidate> getAllCandidates();
    
    /**
     * Obtiene el número total de candidatos.
     * 
     * @return cantidad de candidatos almacenados
     */
    int getTotalCandidates();
}
