# Use the official Maven image to build the Spring Boot app
FROM maven:3.8.4-openjdk-17 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and install dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Use the official OpenJDK image to run the application
FROM openjdk:17-jdk-slim

# Set the working directory in the second stage of the Dockerfile
WORKDIR /app

# Copy the built jar file from the build stage (corrected with a destination path)
COPY --from=build /app/target/trainerTracker-server-0.0.1-SNAPSHOT.jar /app/trainerTracker-server-0.0.1-SNAPSHOT.jar

# Expose the port on which the app will run
EXPOSE 8080

# Run the Spring Boot application
ENTRYPOINT ["java", "-jar", "/app/trainerTracker-server-0.0.1-SNAPSHOT.jar"]
