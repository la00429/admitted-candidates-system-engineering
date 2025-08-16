package co.edu.uptc.admitted_candidates_system_engineering;

import co.edu.uptc.admitted_candidates_system_engineering.controller.AdmissionController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Aplicación principal del sistema de candidatos admitidos.
 * Aplica principio KISS: Flujo de ejecución simple y directo.
 * Aplica principio Single Responsibility: solo inicia la aplicación y delega al controlador.
 * Aplica principio Loose Coupling: depende del controlador mediante interfaz.
 */
@SpringBootApplication
public class AdmittedCandidatesSystemEngineeringApplication implements CommandLineRunner {

    @Autowired
    private AdmissionController admissionController;

    public static void main(String[] args) {
        SpringApplication.run(AdmittedCandidatesSystemEngineeringApplication.class, args);
    }

    /**
     * Ejecuta el flujo principal de la aplicación.
     * Aplica principio KISS: Delega toda la lógica al controlador.
     * Aplica principio Single Responsibility: solo inicia el proceso.
     */
    @Override
    public void run(String... args) throws Exception {
        admissionController.executeAdmissionProcess();
    }
}
