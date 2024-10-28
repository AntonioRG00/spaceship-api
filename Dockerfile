# Etapa 1: Compilación
FROM jelastic/maven:3.9.5-openjdk-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/target/spaceshipapi.jar spaceshipapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spaceshipapi.jar"]
