FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8087
CMD ["java", "-jar", "app.jar"]
