FROM amazoncorretto:17
COPY target/token-microservice-0.0.1-SNAPSHOT.jar /app/token-microservice-0.0.1-SNAPSHOT.jar
WORKDIR /app
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/app/token-microservice-0.0.1-SNAPSHOT.jar"]