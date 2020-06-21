FROM openjdk:11-jdk-slim

WORKDIR /

ADD ./build/libs/gateway-*.jar /app/gateway.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/gateway.jar"]