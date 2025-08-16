package co.edu.uptc.admitted_candidates_system_engineering.view;

/**
 * Interfaz para manejar todos los mensajes del sistema.
 * Aplica principio Single Responsibility: solo se encarga de mostrar mensajes.
 * Aplica principio "Program to Interfaces, not Implementations".
 */
public interface IMessagePrinter {
    
    /**
     * Muestra mensaje de inicio del sistema.
     */
    void printSystemStart();
    
    /**
     * Muestra mensaje de carga de datos.
     */
    void printDataLoading();
    
    /**
     * Muestra mensaje de éxito del sistema.
     */
    void printSystemSuccess();
    
    /**
     * Muestra mensaje de error del sistema.
     * 
     * @param error excepción ocurrida
     */
    void printSystemError(Exception error);
}
