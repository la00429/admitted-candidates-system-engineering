package co.edu.uptc.admitted_candidates_system_engineering.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import co.edu.uptc.admitted_candidates_system_engineering.model.Candidate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Servicio para cargar datos de candidatos desde archivo JSON.
 * Aplica principio KISS: Lógica simple y directa para carga de datos.
 * Aplica principio DRY: Centraliza la lógica de deserialización JSON.
 * Aplica principio YAGNI: Solo funcionalidad de carga necesaria.
 */
@Service
public class CandidateDataLoader {
    
    private final ObjectMapper objectMapper;
    
    public CandidateDataLoader() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
    }
    
    /**
     * Carga candidatos desde archivo JSON en el classpath.
     * Aplica principio KISS: Método directo para carga de datos.
     * 
     * @param filename nombre del archivo JSON
     * @return lista de candidatos cargados
     * @throws IOException si hay error al leer el archivo
     */
    public List<Candidate> loadCandidatesFromJson(String filename) throws IOException {
        ClassPathResource resource = new ClassPathResource(filename);
        
        try (InputStream inputStream = resource.getInputStream()) {
            TypeReference<List<Candidate>> typeReference = new TypeReference<List<Candidate>>() {};
            return objectMapper.readValue(inputStream, typeReference);
        }
    }
    
    /**
     * Carga candidatos desde el archivo predeterminado.
     * Aplica principio DRY: Evita repetir el nombre del archivo.
     * 
     * @return lista de candidatos desde candidates.json
     * @throws IOException si hay error al leer el archivo
     */
    public List<Candidate> loadDefaultCandidates() throws IOException {
        return loadCandidatesFromJson("candidates.json");
    }
}
