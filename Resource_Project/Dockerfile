FROM openjdk:11
COPY target/resource-project-1.0.jar app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=kuber", "-jar","/app.jar"]
###EXPOSE 8081