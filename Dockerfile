FROM eclipse-temurin:17-jdk-alpine

# 1. Crear directorio app
WORKDIR /app

# 2. Copiar mvnw y darle permisos
COPY mvnw .
RUN chmod +x mvnw

# 3. Copiar wrapper
COPY .mvn .mvn

# 4. Descargar dependencias offline
RUN ./mvnw dependency:go-offline

# 5. Copiar el proyecto
COPY src ./src
COPY pom.xml .

# 6. Build del jar
RUN ./mvnw -DskipTests package

# 7. Ejecutar la app
CMD ["java", "-jar", "target/*.jar"]
