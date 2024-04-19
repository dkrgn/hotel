FROM openjdk:21
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 8083
CMD ["java", "-jar", "app.jar"]
