# 🎓 Sistema de Candidatos Admitidos - Ingeniería de Sistemas 2025-1

## 📋 Descripción del Proyecto

Sistema desarrollado en **Spring Boot** que permite mostrar la información de candidatos admitidos al programa de Ingeniería de Sistemas y Computación, ordenados según reglas específicas de admisión que incluyen criterios de equidad para comunidades minoritarias.

## 🎯 Características Principales

### ✅ Funcionalidades Implementadas

- **Carga de datos desde JSON**: Los candidatos se cargan automáticamente desde un archivo JSON
- **Ordenamiento inteligente**: Aplica reglas de prioridad para comunidades minoritarias y criterios académicos
- **Visualización en tabla**: Muestra los resultados en formato tabular claro y profesional
- **Estadísticas del proceso**: Genera reportes automáticos con métricas del proceso de admisión

### 📊 Reglas de Ordenamiento

1. **Prioridad para comunidades minoritarias** (Indígenas, Afrocolombianos, Rom, Raizales, Palenqueros)
2. **Puntaje global ICFES** (descendente - mayor a menor)
3. **Puntaje de matemáticas** (en caso de empate)
4. **Puntaje de inglés** (en caso de empate)
5. **Orden alfabético** (criterio final)

## 🏗️ Arquitectura del Sistema

### 📦 Estructura de Paquetes

```
co.edu.uptc.admitted_candidates_system_engineering/
├── 📂 model/
│   └── 📄 Candidate.java
├── 📂 repository/
│   ├── 📄 CandidateRepository.java (Interfaz)
│   └── 📄 InMemoryCandidateRepository.java
├── 📂 service/
│   ├── 📄 Sorter.java (Interfaz)
│   ├── 📄 CandidateSorter.java
│   └── 📄 CandidateDataLoader.java
├── 📂 view/
│   └── 📄 TablePrinter.java
└── 📄 AdmittedCandidatesSystemEngineeringApplication.java
```

### 🔧 Tecnologías Utilizadas

- **Spring Boot 3.2.0**
- **Java 17**
- **Jackson** (para procesamiento JSON)
- **Maven** (gestión de dependencias)

## 🎨 Principios de Diseño Aplicados

### 1. 💡 KISS (Keep It Simple, Stupid)
- **Aplicación**: Código claro y directo sin estructuras innecesarias
- **Ejemplo**: Método `isMinorityCommunity()` con lógica simple y comprensible
- **Beneficio**: Facilita mantenimiento y comprensión del código

### 2. 🔄 DRY (Don't Repeat Yourself)
- **Aplicación**: Centralización de lógica común y reutilización de componentes
- **Ejemplo**: Comparador único en `CandidateSorter` para todas las reglas de ordenamiento
- **Beneficio**: Evita duplicación y facilita cambios centralizados

### 3. 🚫 YAGNI (You Aren't Gonna Need It)
- **Aplicación**: Solo funcionalidades requeridas en el caso de uso
- **Ejemplo**: Repositorio sin métodos como `update()` o `delete()` no necesarios
- **Beneficio**: Código más limpio y enfocado en requisitos específicos

### 4. 🔌 Program to Interfaces, not Implementations
- **Aplicación**: Uso de interfaces para definir contratos
- **Ejemplo**: `CandidateRepository` y `Sorter<T>` como interfaces
- **Beneficio**: Flexibilidad para intercambiar implementaciones

### 5. 🔗 Loose Coupling
- **Aplicación**: Componentes independientes que se comunican mediante interfaces
- **Ejemplo**: Inyección de dependencias en la aplicación principal
- **Beneficio**: Facilita testing y modificación sin afectar otros componentes

## 🚀 Instrucciones de Ejecución

### 📋 Prerrequisitos
- Java 17 o superior
- Maven 3.6+

### ⚡ Ejecución Rápida

```bash
# 1. Compilar el proyecto
mvn clean package

# 2. Ejecutar la aplicación
java -jar target/admitted-candidates-system-0.0.1-SNAPSHOT.jar
```

### 🎯 Ejecución con Maven

```bash
mvn spring-boot:run
```

## 📊 Salida Esperada

```
🚀 Iniciando Sistema de Candidatos Admitidos...
📂 Cargando datos de candidatos...
✅ Se cargaron 15 candidatos exitosamente.

==============================================================================
                    📊 CANDIDATOS ADMITIDOS - INGENIERÍA DE SISTEMAS 2025-1
==============================================================================
+----------------------------------------------------------------------------------------------------+
| POS  | NOMBRE               | APELLIDO             | ICFES TOTAL  | MATEMÁT. | INGLÉS   | ETNIA           |
+----------------------------------------------------------------------------------------------------+
| 1*   | María Fernanda       | González Rodríguez   | 385          | 92       | 88       | Indígena Wayuu  |
| 2*   | Ana Lucía            | Vargas Castillo      | 375          | 88       | 90       | Afrocolombiano  |
...
```

## 📈 Estadísticas Generadas

- **Total de candidatos admitidos**
- **Cantidad y porcentaje de candidatos de comunidades minoritarias**
- **Puntajes ICFES: promedio, máximo y mínimo**
- **Top 3 candidatos destacados**

## 📁 Datos de Ejemplo

El sistema incluye 15 candidatos de ejemplo con datos realistas que representan:
- **5 candidatos de comunidades minoritarias** (33.3%)
- **10 candidatos de población general** (66.7%)
- **Rango de puntajes ICFES**: 360-395 puntos
- **Diversidad étnica**: Wayuu, Emberá, Afrocolombiano, Rom, Palenquero

## 🏆 Características Destacadas

### ✨ Interfaz de Usuario
- Tabla formateada con caracteres Unicode
- Indicadores visuales para comunidades minoritarias (*)
- Separadores para mejor legibilidad
- Colores y emojis para mejor experiencia

### 🔍 Precisión del Ordenamiento
- Implementa correctamente todas las reglas especificadas
- Maneja empates según criterios establecidos
- Prioriza equitativamente las comunidades minoritarias

### 📊 Análisis Automático
- Cálculo automático de estadísticas
- Identificación del top de candidatos
- Métricas de diversidad e inclusión

## 🤝 Contribución

Este sistema fue desarrollado siguiendo buenas prácticas de ingeniería de software y puede ser extendido fácilmente para incluir nuevas funcionalidades como:

- Persistencia en base de datos
- API REST para consultas
- Filtros por criterios específicos
- Exportación a diferentes formatos
- Interfaz web interactiva

---

**Desarrollado con ❤️ usando Spring Boot y aplicando principios sólidos de diseño de software**
