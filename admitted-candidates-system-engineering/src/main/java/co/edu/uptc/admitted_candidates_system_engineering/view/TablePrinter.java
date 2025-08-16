package co.edu.uptc.admitted_candidates_system_engineering.view;

import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Componente para imprimir tablas de candidatos en consola.
 * Aplica principio KISS: Formato simple y claro para la salida.
 * Aplica principio DRY: Métodos reutilizables para formateo.
 * Aplica principio YAGNI: Solo funcionalidad de impresión requerida.
 */
@Component
public class TablePrinter {
    
    private static final String SEPARATOR = "+" + "-".repeat(100) + "+";
    private static final String HEADER_FORMAT = "| %-4s | %-20s | %-20s | %-12s | %-8s | %-8s | %-15s |";
    private static final String ROW_FORMAT = "| %-4s | %-20s | %-20s | %-12d | %-8d | %-8d | %-15s |";
    
    /**
     * Imprime la tabla de candidatos ordenados.
     * Aplica principio KISS: Formato claro y directo.
     * 
     * @param candidates lista de candidatos a mostrar
     */
    public void printTable(List<Candidate> candidates) {
        if (candidates == null || candidates.isEmpty()) {
            System.out.println("\n❌ No hay candidatos para mostrar.");
            return;
        }
        
        printHeader();
        printCandidates(candidates);
        printFooter(candidates);
    }
    
    /**
     * Imprime el encabezado de la tabla.
     * Aplica principio DRY: Evita repetir el formato del encabezado.
     */
    private void printHeader() {
        System.out.println("\n" + "=".repeat(110));
        System.out.println("                    📊 CANDIDATOS ADMITIDOS - INGENIERÍA DE SISTEMAS 2025-1");
        System.out.println("=".repeat(110));
        System.out.println(SEPARATOR);
        System.out.printf(HEADER_FORMAT, 
            "POS", "NOMBRE", "APELLIDO", "ICFES TOTAL", "MATEMÁT.", "INGLÉS", "ETNIA");
        System.out.println();
        System.out.println(SEPARATOR);
    }
    
    /**
     * Imprime la lista de candidatos.
     * Aplica principio DRY: Reutiliza formato para cada fila.
     * 
     * @param candidates lista de candidatos
     */
    private void printCandidates(List<Candidate> candidates) {
        for (int i = 0; i < candidates.size(); i++) {
            Candidate candidate = candidates.get(i);
            String ethnicity = formatEthnicity(candidate.getEthnicity());
            
            // Añadir indicador para comunidades minoritarias
            String positionIndicator = String.valueOf(i + 1);
            if (candidate.isMinorityCommunity()) {
                positionIndicator += "*";
            }
            
            System.out.printf(ROW_FORMAT,
                positionIndicator,
                truncateString(candidate.getName(), 20),
                truncateString(candidate.getLastName(), 20),
                candidate.getGlobalIcfesScore(),
                candidate.getMathScore(),
                candidate.getEnglishScore(),
                ethnicity);
            System.out.println();
            
            // Línea separadora cada 5 candidatos para mejor legibilidad
            if ((i + 1) % 5 == 0 && i < candidates.size() - 1) {
                System.out.println("|" + "-".repeat(100) + "|");
            }
        }
    }
    
    /**
     * Imprime el pie de la tabla con estadísticas.
     * 
     * @param candidates lista de candidatos para calcular estadísticas
     */
    private void printFooter(List<Candidate> candidates) {
        long minorityCandidates = candidates.stream()
            .mapToLong(candidate -> candidate.isMinorityCommunity() ? 1 : 0)
            .sum();
            
        System.out.println(SEPARATOR);
        System.out.printf("| %-96s |\n", 
            String.format("📈 Total de candidatos admitidos: %d", candidates.size()));
        System.out.printf("| %-96s |\n", 
            String.format("🏛️  Candidatos de comunidades minoritarias: %d", minorityCandidates));
        System.out.printf("| %-96s |\n", "* = Candidato de comunidad minoritaria");
        System.out.println(SEPARATOR);
        System.out.println("✅ Tabla generada exitosamente\n");
    }
    
    /**
     * Formatea la etnia para mostrar en la tabla.
     * Aplica principio KISS: Lógica simple de formateo.
     * 
     * @param ethnicity etnia original
     * @return etnia formateada
     */
    private String formatEthnicity(String ethnicity) {
        if (ethnicity == null || ethnicity.trim().isEmpty()) {
            return "General";
        }
        return truncateString(ethnicity, 15);
    }
    
    /**
     * Trunca un string si excede la longitud máxima.
     * Aplica principio DRY: Reutiliza lógica de truncado.
     * 
     * @param str string a truncar
     * @param maxLength longitud máxima
     * @return string truncado
     */
    private String truncateString(String str, int maxLength) {
        if (str == null) return "";
        if (str.length() <= maxLength) return str;
        return str.substring(0, maxLength - 3) + "...";
    }
}
