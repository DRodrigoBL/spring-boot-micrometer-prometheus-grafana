FROM adoptopenjdk/openjdk11-openj9
WORKDIR /app
COPY build/libs/rest-api-monitoring-0.0.1.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar",  "--spring.config.location=file:/app/application.properties", "app.jar"]