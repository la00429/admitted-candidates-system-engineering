package co.edu.uptc.admitted_candidates_system_engineering.service;

import java.util.List;

/**
 * Interfaz para ordenamiento de candidatos.
 * Aplica principio "Program to Interfaces, not Implementations":
 * Define contrato de ordenamiento sin especificar implementación.
 * Aplica principio Loose Coupling: Permite diferentes estrategias de ordenamiento.
 */
public interface ISorter<T> {
    
    /**
     * Ordena una lista de elementos según criterios específicos.
     * 
     * @param items lista de elementos a ordenar
     * @return lista ordenada de elementos
     */
    List<T> sort(List<T> items);
}
