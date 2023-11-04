FROM openjdk:11
COPY target/song-1.0.jar app.jar
ENTRYPOINT ["java","-Dspring.profiles.active=kuber", "-jar","/app.jar"]
###EXPOSE 8080