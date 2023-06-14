FROM openjdk:17

COPY target/Client-0.0.1-SNAPSHOT.jar /app/Client.jar

CMD java -jar /app/Client.jar