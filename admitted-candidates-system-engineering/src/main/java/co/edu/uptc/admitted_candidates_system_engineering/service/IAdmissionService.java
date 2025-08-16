package co.edu.uptc.admitted_candidates_system_engineering.service;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import java.util.List;

/**
 * Interfaz para el servicio de gestión del proceso de admisión.
 * Aplica principio "Program to Interfaces, not Implementations".
 * Aplica principio Single Responsibility: se encarga únicamente del proceso de admisión.
 */
public interface IAdmissionService {
    
    /**
     * Inicializa el sistema cargando los datos de candidatos.
     * 
     * @throws Exception si hay error en la carga de datos
     */
    void initializeSystem() throws Exception;
    
    /**
     * Procesa el listado de candidatos admitidos aplicando las reglas de ordenamiento.
     * 
     * @return lista ordenada de candidatos según criterios de admisión
     */
    List<Candidate> processAdmittedCandidates();
    
    
    /**
     * Obtiene el total de candidatos cargados en el sistema.
     * 
     * @return número total de candidatos
     */
    int getTotalCandidates();
}
