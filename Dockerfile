# Etapa 1: Build - Usamos Maven con JDK 21
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app

# Copiar el archivo pom.xml para descargar dependencias primero (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package


FROM eclipse-temurin:21-jre-alpine AS run
WORKDIR /app

EXPOSE 8080

COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]