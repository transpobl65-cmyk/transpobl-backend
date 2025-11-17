# ---------- Build ----------
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copiar archivos esenciales
COPY pom.xml .
COPY mvnw .
COPY .mvn .mvn
COPY mvnw.cmd .


# Descargar dependencias sin compilar
RUN ./mvnw dependency:go-offline -B

# Copiar el código fuente
COPY src ./src

# Compilar el proyecto (saltando tests)
RUN ./mvnw package -DskipTests

# ---------- Run ----------
FROM eclipse-temurin:17-jdk
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
