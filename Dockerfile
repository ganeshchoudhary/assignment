
FROM openjdk:latest
WORKDIR /app
COPY ./target/*.jar app.jar
EXPOSE 8080
COPY ./target/*.jar app.jar

HEALTHCHECK --interval=5m --timeout=3s CMD curl http://localhost:8080/app/health

ENTRYPOINT ["java", "-jar", "app.jar"]