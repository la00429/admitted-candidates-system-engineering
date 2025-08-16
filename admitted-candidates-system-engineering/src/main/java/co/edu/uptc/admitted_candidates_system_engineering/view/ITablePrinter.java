package co.edu.uptc.admitted_candidates_system_engineering.view;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import java.util.List;

/**
 * Interfaz para imprimir tablas de candidatos.
 * Aplica principio "Program to Interfaces, not Implementations".
 * Aplica principio Single Responsibility: solo se encarga de mostrar tablas.
 */
public interface ITablePrinter {
    
    /**
     * Imprime una tabla con los candidatos proporcionados.
     * 
     * @param candidates lista de candidatos a mostrar
     */
    void printTable(List<Candidate> candidates);
}
