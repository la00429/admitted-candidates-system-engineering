package co.edu.uptc.admitted_candidates_system_engineering.view;

import org.springframework.stereotype.Component;

/**
 * Implementación para manejar todos los mensajes del sistema.
 * Aplica principio Single Responsibility: solo se encarga de mostrar mensajes.
 * Aplica principio KISS: mensajes simples y directos.
 */
@Component
public class MessagePrinter implements IMessagePrinter {
    
    @Override
    public void printSystemStart() {
        System.out.println(" Iniciando Sistema de Candidatos Admitidos...");
    }
    
    @Override
    public void printDataLoading() {
        System.out.println(" Cargando datos de candidatos...");
    }
    
    @Override
    public void printDataLoadSuccess(int candidateCount) {
        System.out.printf(" Se cargaron %d candidatos exitosamente.%n", candidateCount);
    }
    
    @Override
    public void printSystemSuccess() {
        System.out.println("| Sistema ejecutado exitosamente.");
    }
    
    @Override
    public void printSystemError(Exception error) {
        System.err.println("| Error al ejecutar la aplicación: " + error.getMessage());
        error.printStackTrace();
    }
}