# 📋 APLICACIÓN DE PRINCIPIOS DE DISEÑO

## 🎯 Sistema de Candidatos Admitidos - Ingeniería de Sistemas 2025-1

Este documento explica cómo se aplicaron los 5 principios de diseño solicitados en el desarrollo del sistema.

---

## 1. 💡 KISS (Keep It Simple, Stupid)

**Principio**: "El código debe ser claro y directo, sin estructuras innecesarias ni nombres ambiguos."

### ✅ Aplicación en el Proyecto:

**📁 Clase `Candidate`:**
```java
// Nombres de métodos claros y directos
public boolean isMinorityCommunity() {
    return ethnicity != null && 
           (ethnicity.toLowerCase().contains("indígena") || 
            ethnicity.toLowerCase().contains("afrocolombiano"));
}
```

**📁 Clase `TablePrinter`:**
```java
// Formato simple y directo para mostrar datos
private static final String ROW_FORMAT = "| %-4d | %-20s | %-20s | %-12d |";
```

**📁 Flujo principal en `AdmittedCandidatesSystemApplication`:**
```java
// Secuencia clara de operaciones sin complejidad innecesaria
1. Cargar datos
2. Obtener candidatos
3. Ordenar candidatos
4. Mostrar tabla
```

### 🎯 Beneficios Obtenidos:
- Código fácil de leer y mantener
- Nombres de variables y métodos autoexplicativos
- Flujo de ejecución lineal y comprensible

---

## 2. 🔄 DRY (Don't Repeat Yourself)

**Principio**: "Evitar repeticiones dentro del código al mínimo posible."

### ✅ Aplicación en el Proyecto:

**📁 Clase `CandidateSorter`:**
```java
// Centraliza la lógica de comparación en un solo lugar
private Comparator<Candidate> createCandidateComparator() {
    return Comparator
        .<Candidate>comparingInt(candidate -> candidate.isMinorityCommunity() ? 0 : 1)
        .thenComparing(Candidate::getGlobalIcfesScore, Comparator.reverseOrder());
}
```

**📁 Clase `TablePrinter`:**
```java
// Reutiliza formato para todas las filas
private String truncateString(String str, int maxLength) {
    // Lógica centralizada para truncar strings
}
```

**📁 Clase `CandidateDataLoader`:**
```java
// Evita duplicar configuración de ObjectMapper
public CandidateDataLoader() {
    this.objectMapper = new ObjectMapper();
    this.objectMapper.registerModule(new JavaTimeModule());
}
```

### 🎯 Beneficios Obtenidos:
- Eliminación de código duplicado
- Facilita el mantenimiento (cambios en un solo lugar)
- Reduce la posibilidad de errores por inconsistencia

---

## 3. 🚫 YAGNI (You Aren't Gonna Need It)

**Principio**: "No implementar funcionalidades o tareas no requeridas. Solo lo especificado en el caso de uso."

### ✅ Aplicación en el Proyecto:

**📁 Interfaz `CandidateRepository`:**
```java
public interface CandidateRepository {
    void addCandidate(Candidate candidate);
    List<Candidate> getAllCandidates();
    int getTotalCandidates();
    // ❌ NO implementamos: updateCandidate, deleteCandidate, findById
    // Porque no son requeridos en el caso de uso
}
```

**📁 Clase `Candidate`:**
```java
// Solo atributos requeridos en el caso de uso
private String name;
private String lastName;
private String ethnicity;
private int globalIcfesScore;
private int mathScore;
private int englishScore;
// ❌ NO añadimos: id, email, phone, address (no requeridos)
```

**📁 Configuración `application.properties`:**
```properties
# Solo configuraciones necesarias
logging.level.com.university=INFO
spring.application.name=admitted-candidates-system
# ❌ NO incluimos configuraciones de BD, cache, etc. (no necesarias)
```

### 🎯 Beneficios Obtenidos:
- Código más limpio y enfocado
- Menor complejidad del sistema
- Desarrollo más rápido y eficiente

---

## 4. 🔌 Program to Interfaces, not Implementations

**Principio**: "Usar interfaces en lugar de depender de clases específicas para facilitar el trabajo en equipo."

### ✅ Aplicación en el Proyecto:

**📁 Interfaz `CandidateRepository`:**
```java
public interface CandidateRepository {
    void addCandidate(Candidate candidate);
    List<Candidate> getAllCandidates();
}
```

**📁 Interfaz `Sorter<T>`:**
```java
public interface Sorter<T> {
    List<T> sort(List<T> items);
}
```

**📁 Uso en `AdmittedCandidatesSystemApplication`:**
```java
@Autowired
private CandidateRepository candidateRepository;  // ✅ Interfaz

@Autowired
private Sorter<Candidate> candidateSorter;        // ✅ Interfaz

// Spring inyecta automáticamente las implementaciones:
// - InMemoryCandidateRepository
// - CandidateSorter
```

### 🎯 Beneficios Obtenidos:
- Flexibilidad para cambiar implementaciones
- Facilita testing con mocks
- Equipos pueden trabajar en paralelo en diferentes implementaciones
- Cumple con principios SOLID (Dependency Inversion)

---

## 5. 🔗 Loose Coupling

**Principio**: "Las clases no deben depender directamente unas de otras, sino comunicarse a través de parámetros o interfaces."

### ✅ Aplicación en el Proyecto:

**📁 Separación de Responsabilidades:**
```
Model/      -> Candidate (solo datos y lógica de negocio)
Repository/ -> Gestión de datos (sin conocer presentación)
Service/    -> Lógica de ordenamiento (sin conocer almacenamiento)
View/       -> Presentación (sin conocer lógica de negocio)
```

**📁 Comunicación a través de interfaces:**
```java
// TablePrinter no conoce cómo se obtienen o ordenan los candidatos
public void printTable(List<Candidate> candidates) {
    // Solo se encarga de mostrar
}

// CandidateSorter no conoce cómo se almacenan los candidatos
public List<Candidate> sort(List<Candidate> candidates) {
    // Solo se encarga de ordenar
}
```

**📁 Inyección de Dependencias:**
```java
// La clase principal no instancia directamente las dependencias
@Autowired private CandidateRepository candidateRepository;
@Autowired private Sorter<Candidate> candidateSorter;
@Autowired private TablePrinter tablePrinter;
```

### 🎯 Beneficios Obtenidos:
- Clases independientes y reutilizables
- Facilita testing unitario
- Permite modificar una clase sin afectar las demás
- Facilita trabajo en equipo sin conflictos

---

## 🏗️ ARQUITECTURA RESULTANTE

```
📦 com.university
├── 📂 model/
│   └── 📄 Candidate.java              (KISS, DRY)
├── 📂 repository/
│   ├── 📄 CandidateRepository.java    (Interfaces, Loose Coupling)
│   └── 📄 InMemoryCandidateRepository.java (YAGNI, DRY)
├── 📂 service/
│   ├── 📄 Sorter.java                 (Interfaces, Loose Coupling)
│   ├── 📄 CandidateSorter.java        (KISS, DRY)
│   └── 📄 CandidateDataLoader.java    (KISS, YAGNI)
├── 📂 view/
│   └── 📄 TablePrinter.java           (KISS, DRY, Loose Coupling)
└── 📄 AdmittedCandidatesSystemApplication.java (YAGNI, Loose Coupling)
```

## 🚀 EJECUCIÓN DEL SISTEMA

### Comandos para ejecutar:
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn spring-boot:run
```

### Salida esperada:
```
🚀 Iniciando Sistema de Candidatos Admitidos...
📂 Cargando datos de candidatos...
✅ Se cargaron 15 candidatos exitosamente.

====================================================================================================
                    📊 CANDIDATOS ADMITIDOS - INGENIERÍA DE SISTEMAS 2025-1
====================================================================================================
+----------------------------------------------------------------------------------------------------+
| POS  | NOMBRE               | APELLIDO             | ICFES TOTAL  | MATEMÁT. | INGLÉS   | ETNIA           |
+----------------------------------------------------------------------------------------------------+
| 1*   | María Fernanda       | González Rodríguez   |          385 |       92 |       88 | Indígena Wayuu  |
| 2*   | Ana Lucía            | Vargas Castillo      |          375 |       88 |       90 | Afrocolombiano  |
| 3    | Valeria Nicole       | Restrepo Ospina      |          395 |       94 |       91 | General         |
...
```

## ✅ CONCLUSIÓN

El sistema implementa exitosamente los 5 principios de diseño solicitados, resultando en:

- **Código limpio y mantenible** (KISS)
- **Eliminación de redundancias** (DRY) 
- **Enfoque en requisitos específicos** (YAGNI)
- **Flexibilidad mediante interfaces** (Program to Interfaces)
- **Componentes independientes** (Loose Coupling)

Esta arquitectura facilita el trabajo en equipo, el mantenimiento del código y futuras extensiones del sistema.
