FROM openjdk:19-jdk-alpine

WORKDIR /app/spring-sensor-consumer

RUN rm -rf /app/spring-sensor-consumer/*

COPY ./target/spring-sensor-consumer-0.0.1-SNAPSHOT.jar /app/spring-sensor-consumer/spring-sensor-consumer.jar

EXPOSE 8080

CMD ["java","-jar","/app/spring-sensor-consumer/spring-sensor-consumer.jar"]