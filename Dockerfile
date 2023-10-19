FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar library-0.0.1.jar
ENTRYPOINT ["java","-jar","/library-0.0.1.jar"]