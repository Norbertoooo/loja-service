FROM eclipse-temurin:17-jdk-alpine
COPY target/loja-service-*.jar loja-service.jar
CMD ["java", "-jar", "loja-service.jar"]