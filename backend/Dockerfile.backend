FROM eclipse-temurin:17-jdk-alpine

WORKDIR /standing-svc

COPY target/*.jar standing-svc.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "standing-svc.jar"]