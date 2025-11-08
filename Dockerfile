FROM eclipse-temurin:17
WORKDIR /app
COPY target/Cumulonimbus-1.0.jar /app/Cumulonimbus-1.0.jar
EXPOSE 8080
CMD ["java", "-jar", "Cumulonimbus-1.0.jar"]
