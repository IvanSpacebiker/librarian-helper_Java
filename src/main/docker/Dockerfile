FROM eclipse-temurin:17-jdk-alpine

ARG JAR_FILE=library-0.1.0.jar
COPY ${JAR_FILE} application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]