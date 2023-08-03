FROM openjdk:20
LABEL authors="godzilla"
ADD target/VidClick.jar app.jar

ENV MONGO_HOST vidclick-mongo
ENV MONGO_PORT 27017

ENTRYPOINT ["java", "-jar", "app.jar"]
