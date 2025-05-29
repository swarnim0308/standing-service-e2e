# ------------ Build Stage ------------
FROM eclipse-temurin:17-jdk-jammy AS builder

WORKDIR /standing-service

COPY . .

RUN ./mvnw clean package -DskipTests

# ------------ Runtime Stage ------------
FROM eclipse-temurin:17-jre-jammy

WORKDIR /standing-service

# Copy the JAR
COPY --from=builder /standing-service/target/*.jar app.jar

EXPOSE 8080

# Use the external config file
ENTRYPOINT ["java", "-jar", "app.jar"]
