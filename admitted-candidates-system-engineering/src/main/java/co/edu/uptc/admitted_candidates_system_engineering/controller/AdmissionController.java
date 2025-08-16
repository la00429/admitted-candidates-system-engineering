package co.edu.uptc.admitted_candidates_system_engineering.controller;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import co.edu.uptc.admitted_candidates_system_engineering.service.AdmissionStatistics;
import co.edu.uptc.admitted_candidates_system_engineering.service.IAdmissionService;
import co.edu.uptc.admitted_candidates_system_engineering.view.ITablePrinter;
import co.edu.uptc.admitted_candidates_system_engineering.view.IStatisticsPrinter;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final IStatisticsPrinter statisticsPrinter;
    
    @Autowired
    public AdmissionController(IAdmissionService admissionService,
                              ITablePrinter tablePrinter,
                              IStatisticsPrinter statisticsPrinter) {
        this.admissionService = admissionService;
        this.tablePrinter = tablePrinter;
        this.statisticsPrinter = statisticsPrinter;
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
            printDataLoadSuccess();
            
            // 2. Procesar candidatos
            List<Candidate> admittedCandidates = admissionService.processAdmittedCandidates();
            
            // 3. Mostrar resultados
            tablePrinter.printTable(admittedCandidates);
            
            // 4. Mostrar estadísticas
            AdmissionStatistics statistics = admissionService.generateStatistics(admittedCandidates);
            statisticsPrinter.printStatistics(statistics);
            
            // 5. Finalizar
            printSystemSuccess();
            
        } catch (Exception e) {
            printSystemError(e);
        }
    }
    
    private void printSystemStart() {
        System.out.println(" Iniciando Sistema de Candidatos Admitidos...");
        System.out.println(" Cargando datos de candidatos...");
    }
    
    private void printDataLoadSuccess() {
        System.out.printf(" Se cargaron %d candidatos exitosamente.%n", 
                         admissionService.getTotalCandidates());
    }
    
    private void printSystemSuccess() {
        System.out.println("| Sistema ejecutado exitosamente.");
    }
    
    private void printSystemError(Exception e) {
        System.err.println("| Error al ejecutar la aplicación: " + e.getMessage());
        e.printStackTrace();
    }
}
