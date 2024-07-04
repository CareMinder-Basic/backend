# Use the official OpenJDK 21 image from the Docker Hub
FROM openjdk:21-jdk-alpine

# Create a volume directory
VOLUME /tmp

# Copy the jar file into the container
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java", "-jar", "/app.jar"]