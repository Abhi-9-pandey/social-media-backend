# Stage 1: Build the application using Java 17
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app
COPY . .
# Grant execution permissions and build the jar
RUN chmod +x mvnw
RUN ./mvnw clean package -DskipTests

# Stage 2: Create a minimal environment to run the application
FROM eclipse-temurin:17-jre
WORKDIR /app
# Copy the built JAR from the previous stage
COPY --from=build /app/target/*.jar app.jar
# Standard Spring Boot port
EXPOSE 8080
# Command to execute the application
ENTRYPOINT ["java", "-jar", "app.jar"]