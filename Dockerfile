FROM openjdk:20
LABEL authors="godzilla"
ADD target/VidClick.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
