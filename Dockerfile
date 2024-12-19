FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/musify-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ARG SPRING_PROFILE=dev
ENV SPRING_PROFILES_ACTIVE=$SPRING_PROFILE

CMD ["java", "-jar", "app.jar"]

