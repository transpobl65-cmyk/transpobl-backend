# 1️⃣ — Etapa de construcción
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos de Maven primero (para aprovechar caché)
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY .mvn .mvn

# Descargar dependencias sin fallar
RUN mvn dependency:go-offline -B

# Copiar el resto del proyecto
COPY src ./src

# Compilar
RUN mvn package -DskipTests

# 2️⃣ — Etapa de ejecución
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copiar el JAR generado desde la etapa build
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
