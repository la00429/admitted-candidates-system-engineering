package co.edu.uptc.admitted_candidates_system_engineering.controller;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import co.edu.uptc.admitted_candidates_system_engineering.service.IAdmissionService;
import co.edu.uptc.admitted_candidates_system_engineering.view.ITablePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import co.edu.uptc.admitted_candidates_system_engineering.view.IMessagePrinter;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Controlador que coordina el flujo de la aplicación.
 * Aplica principio Single Responsibility: solo coordina entre servicios y vista.
 * Aplica principio Loose Coupling: depende de interfaces.
 * Actúa como el "Presenter" en el patrón MVP.
 */
@Component
public class AdmissionController {
    
    private final IAdmissionService admissionService;
    private final ITablePrinter tablePrinter;
    private final IMessagePrinter messagePrinter;
    
    @Autowired
    public AdmissionController(IAdmissionService admissionService,
                              ITablePrinter tablePrinter,
                              IMessagePrinter messagePrinter) {
        this.admissionService = admissionService;
        this.tablePrinter = tablePrinter;
        this.messagePrinter = messagePrinter;
    }
    
    /**
     * Ejecuta el flujo completo del proceso de admisión.
     * Aplica principio KISS: flujo lineal y claro.
     */
    public void executeAdmissionProcess() {
        try {
            // 1. Inicializar sistema
            printSystemStart();
            admissionService.initializeSystem();            
            // 2. Procesar candidatos
            List<Candidate> admittedCandidates = admissionService.processAdmittedCandidates();
            
            // 3. Mostrar resultados
            tablePrinter.printTable(admittedCandidates);
            
            // 5. Finalizar
            printSystemSuccess();
            
        } catch (Exception e) {
            printSystemError(e);
        }
    }
    
    private void printSystemStart() {
        messagePrinter.printSystemStart();

    }
    
    private void printSystemSuccess() {
        messagePrinter.printSystemSuccess();
    }
    
    private void printSystemError(Exception e) {
        messagePrinter.printSystemError(e);
    }
}
