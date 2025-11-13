FROM openjdk:26-ea-trixie

WORKDIR /app

# Copy Maven wrapper and pom.xml for caching
COPY mvnw pom.xml ./
COPY .mvn .mvn

# Copy source code
COPY src ./src

# Build app
RUN ./mvnw clean package -DskipTests

# Run app
CMD ["java", "-jar", "target/inventory-system-0.0.1-SNAPSHOT.jar"]
