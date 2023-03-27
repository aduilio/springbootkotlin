FROM eclipse-temurin:17-alpine
EXPOSE 8080
ADD ../target/forum-0.0.1-SNAPSHOT.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
