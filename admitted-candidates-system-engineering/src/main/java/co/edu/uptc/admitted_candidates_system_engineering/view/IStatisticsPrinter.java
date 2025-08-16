package co.edu.uptc.admitted_candidates_system_engineering.view;

import co.edu.uptc.admitted_candidates_system_engineering.service.AdmissionStatistics;

/**
 * Interfaz para imprimir estadísticas del proceso de admisión.
 * Aplica principio "Program to Interfaces, not Implementations".
 * Aplica principio Single Responsibility: solo se encarga de mostrar estadísticas.
 */
public interface IStatisticsPrinter {
    
    /**
     * Imprime las estadísticas del proceso de admisión.
     * 
     * @param statistics objeto con las estadísticas calculadas
     */
    void printStatistics(AdmissionStatistics statistics);
}
