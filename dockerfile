FROM maven:3.8.4-openjdk-11-slim AS build
# Set up the working directory
WORKDIR /playwright
# Copy the POM file
COPY pom.xml .

# Download dependencies
RUN mvn dependency:go-offline -B

# Copy the source code
COPY src/ ./src/

# Build the project
RUN mvn package -DskipTests

FROM selenium/standalone-chrome:latest

# Set up the working directory
WORKDIR /playwright

# Copy the built JAR file from the previous stage
COPY --from=build /playwright/target/examples-0.1-SNAPSHOT.jar .

# Run the tests
CMD ["java", "-jar", "examples-0.1-SNAPSHOT.jar"]