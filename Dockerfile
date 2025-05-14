# Use a lightweight OpenJDK image
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the build.gradle and gradle files to cache dependencies early
COPY build.gradle settings.gradle gradlew /app/
COPY gradle /app/gradle

# Download dependencies (leverages Docker caching)
RUN ./gradlew build -x test --no-daemon || return 0

# Copy the entire project
COPY . /app

# Build the application
RUN ./gradlew bootJar --no-daemon

# Expose port 8080
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "build/libs/VipsConnect-0.0.1-SNAPSHOT.jar"]
