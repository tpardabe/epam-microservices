FROM openjdk:11
COPY target/resource-project-1.0.jar app.jar
ENTRYPOINT ["java", "-jar","/app.jar"]
#"-Dspring.profiles.active=dev",
EXPOSE 8081