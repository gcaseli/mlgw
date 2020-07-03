# Muito Legal Gateway
The project is Java 11 and spring gateway as language and Gradle for dependencies.
The project is dockerized to facilitate deployment, portability, different environments and application execution.

# Docs
http://localhost:8081/api-docs
http://localhost:8081/swagger-ui-custom.html

# Routes
http://localhost:8081/actuator/gateway/routes/{id}
http://localhost:8081/actuator/gateway/routes


# How to build/run
To build the artifact jar file you need to run the following command:
   - ./gradlew clean build

 Build an image from a Dockerfile
  - docker build -t mlgw . 
 Check if the image was created (look for `mlgw` name):
  - docker image ls
 To run the project we can use docker-compose
  - docker-compose up