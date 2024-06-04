# Utiliza una imagen base de Maven para compilar el proyecto
FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Utiliza una imagen base de JDK para ejecutar el proyecto
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/servicio-cuenta-movimientos-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/servicio-cuenta-movimientos-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
