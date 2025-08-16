## CÓMO EJECUTAR EL PROYECTO - SISTEMA DE CANDIDATOS ADMITIDOS

### REQUISITOS PREVIOS
- Java 17 o superior instalado
- Maven (opcional, el proyecto incluye Maven Wrapper)

### PASOS DE EJECUCIÓN

#### Opción 1: Con Maven Wrapper (Recomendado)
1. Ir al directorio del proyecto:
   cd /admitted-candidates-system-engineering/admitted-candidates-system-engineering

2. Compilar el proyecto:
   ./mvnw clean compile

3. Ejecutar la aplicación:
   ./mvnw spring-boot:run

#### Opción 2: Con Maven instalado
1. Ir al directorio del proyecto:
   cd /admitted-candidates-system-engineering/admitted-candidates-system-engineering

2. Compilar el proyecto:
   mvn clean compile

3. Ejecutar la aplicación:
   mvn spring-boot:run

#### Opción 3: Ejecutar el JAR compilado
1. Ir al directorio del proyecto:
   cd /admitted-candidates-system-engineering/admitted-candidates-system-engineering

2. Compilar y empaquetar:
   ./mvnw clean package

3. Ejecutar el JAR:
   java -jar target/admitted-candidates-system-0.0.1-SNAPSHOT.jar

### VERIFICACIÓN
- Verificar Java: java -version
- Verificar que el archivo candidates.json esté en src/main/resources/

### RESULTADO ESPERADO
La aplicación mostrará:
1. Tabla de candidatos ordenados
2. Estadísticas del proceso de admisión
3. Indicadores para comunidades minoritarias (*)

### COMANDOS ADICIONALES
- Ejecutar solo tests: ./mvnw test
- Limpiar proyecto: ./mvnw clean
- Ver ayuda de Maven: ./mvnw help:help

### SOLUCIÓN DE PROBLEMAS
- Si no funciona ./mvnw, usar: chmod +x mvnw
- Si falta Java, instalar OpenJDK 17 o superior
- Si hay errores de compilación, verificar que todas las dependencias estén en pom.xml
