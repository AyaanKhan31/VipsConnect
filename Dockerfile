# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper files
COPY gradlew build.gradle settings.gradle /app/
COPY gradle /app/gradle

# Give execute permission to gradlew
RUN chmod +x gradlew

# Copy the rest of the project
COPY . /app

# Build the application
RUN ./gradlew bootJar --no-daemon

# Expose port
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "build/libs/your-app-name-0.0.1-SNAPSHOT.jar"]
