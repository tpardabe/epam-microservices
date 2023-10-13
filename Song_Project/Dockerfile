FROM openjdk:11
COPY target/song-1.0.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=dev", "-jar","/app.jar"]
EXPOSE 8080