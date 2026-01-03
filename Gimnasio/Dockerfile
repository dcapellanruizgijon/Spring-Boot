#EN EL DOCKERFILE DEFINICMOS COMO SE CONSTRUIRÁ LA IMAGEN DOCKER

# Stage 1: Build the application
FROM eclipse-temurin:17-jdk AS builder
# Set the working directory
WORKDIR /app
# Copy the application code
COPY . .
# Given permissions to mvnw
RUN chmod +x mvnw
# Build the application (requires Maven or Gradle)
RUN ./mvnw clean package -DskipTests
# Stage 2: Run the application
FROM eclipse-temurin:17-jre
# Set the working directory
WORKDIR /app
# Copy the JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar
# Expose the port the app will run on (ES MEJOR USAR EL 8080 ya que Spring a veces ignora el server.port dentro de contenedores)
EXPOSE 8080    
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
