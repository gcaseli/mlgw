FROM openjdk:11-jdk-slim

WORKDIR /

ADD ./build/libs/mlgw-*.jar /app/mlgw.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=container", "-jar", "/app/mlgw.jar"]