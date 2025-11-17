# ---------- Etapa de construcción ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar los archivos esenciales para el cache de Maven
COPY pom.xml .
COPY mvnw .
COPY mvnw .cmd .
COPY .mvn .mvn

# Descargar dependencias sin compilar
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto (saltando tests)
RUN ./mvnw package -DskipTests

# ---------- Etapa de ejecución ----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el jar generado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
