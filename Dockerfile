# Use the official OpenJDK 21 image from the Docker Hub
FROM openjdk:21-jdk

# Create a directory for the application
RUN mkdir -p /app/config

# Copy the application.yml file into the container
COPY src/main/resources/application.yml /app/config/application.yml

# Copy the jar file into the container
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Set the environment variable to use the application.yml file
ENV SPRING_CONFIG_LOCATION=/app/config/application.yml

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]
