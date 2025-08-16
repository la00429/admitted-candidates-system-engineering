package co.edu.uptc.admitted_candidates_system_engineering;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import co.edu.uptc.admitted_candidates_system_engineering.repository.CandidateRepository;
import co.edu.uptc.admitted_candidates_system_engineering.service.CandidateDataLoader;
import co.edu.uptc.admitted_candidates_system_engineering.service.Sorter;
import co.edu.uptc.admitted_candidates_system_engineering.view.TablePrinter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

/**
 * Aplicación principal del sistema de candidatos admitidos.
 * Aplica principio KISS: Flujo de ejecución simple y directo.
 * Aplica principio Loose Coupling: Utiliza interfaces para las dependencias.
 * Aplica principio DRY: Reutiliza componentes existentes.
 */
@SpringBootApplication
public class AdmittedCandidatesSystemEngineeringApplication implements CommandLineRunner {

    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private Sorter<Candidate> candidateSorter;

    @Autowired
    private TablePrinter tablePrinter;

    @Autowired
    private CandidateDataLoader dataLoader;

    public static void main(String[] args) {
        SpringApplication.run(AdmittedCandidatesSystemEngineeringApplication.class, args);
    }

    /**
     * Ejecuta el flujo principal de la aplicación.
     * Aplica principio KISS: Secuencia clara de operaciones.
     * Aplica principio YAGNI: Solo ejecuta lo necesario para el caso de uso.
     */
    @Override
    public void run(String... args) throws Exception {
        System.out.println("🚀 Iniciando Sistema de Candidatos Admitidos...");
        
        try {
            // 1. Cargar datos desde JSON
            loadCandidateData();
            
            // 2. Obtener todos los candidatos
            List<Candidate> allCandidates = candidateRepository.getAllCandidates();
            
            // 3. Ordenar candidatos según reglas de admisión
            List<Candidate> sortedCandidates = candidateSorter.sort(allCandidates);
            
            // 4. Mostrar tabla de resultados
            tablePrinter.printTable(sortedCandidates);
            
            // 5. Mostrar estadísticas adicionales
            printStatistics(sortedCandidates);
            
        } catch (Exception e) {
            System.err.println("❌ Error al ejecutar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
        
        System.out.println("✅ Sistema ejecutado exitosamente.");
    }
    
    /**
     * Carga los datos de candidatos desde el archivo JSON.
     * Aplica principio KISS: Lógica directa de carga.
     */
    private void loadCandidateData() {
        try {
            System.out.println("📂 Cargando datos de candidatos...");
            List<Candidate> candidates = dataLoader.loadDefaultCandidates();
            
            for (Candidate candidate : candidates) {
                candidateRepository.addCandidate(candidate);
            }
            
            System.out.printf("✅ Se cargaron %d candidatos exitosamente.%n", 
                            candidateRepository.getTotalCandidates());
            
        } catch (Exception e) {
            System.err.println("❌ Error al cargar datos: " + e.getMessage());
            throw new RuntimeException("No se pudieron cargar los datos de candidatos", e);
        }
    }
    
    /**
     * Muestra estadísticas adicionales del proceso de admisión.
     * Aplica principio KISS: Información clara y útil.
     * 
     * @param sortedCandidates lista ordenada de candidatos
     */
    private void printStatistics(List<Candidate> sortedCandidates) {
        System.out.println("📊 ESTADÍSTICAS DEL PROCESO DE ADMISIÓN");
        System.out.println("=".repeat(50));
        
        long minorityCandidates = sortedCandidates.stream()
            .mapToLong(candidate -> candidate.isMinorityCommunity() ? 1 : 0)
            .sum();
        
        double averageIcfes = sortedCandidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .average()
            .orElse(0.0);
        
        int maxIcfes = sortedCandidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .max()
            .orElse(0);
        
        int minIcfes = sortedCandidates.stream()
            .mapToInt(Candidate::getGlobalIcfesScore)
            .min()
            .orElse(0);
        
        System.out.printf("👥 Total de candidatos: %d%n", sortedCandidates.size());
        System.out.printf("🏛️  Candidatos de comunidades minoritarias: %d (%.1f%%)%n", 
                         minorityCandidates, 
                         (minorityCandidates * 100.0) / sortedCandidates.size());
        System.out.printf("📈 Puntaje ICFES promedio: %.1f%n", averageIcfes);
        System.out.printf("🥇 Puntaje ICFES máximo: %d%n", maxIcfes);
        System.out.printf("📉 Puntaje ICFES mínimo: %d%n", minIcfes);
        
        // Mostrar top 3 candidatos
        System.out.println("\n🏆 TOP 3 CANDIDATOS:");
        for (int i = 0; i < Math.min(3, sortedCandidates.size()); i++) {
            Candidate candidate = sortedCandidates.get(i);
            String minorityIndicator = candidate.isMinorityCommunity() ? " (Comunidad Minoritaria)" : "";
            System.out.printf("%d. %s %s - ICFES: %d%s%n", 
                             i + 1, 
                             candidate.getName(), 
                             candidate.getLastName(), 
                             candidate.getGlobalIcfesScore(),
                             minorityIndicator);
        }
        
        System.out.println("\n" + "=".repeat(50));
    }
}
