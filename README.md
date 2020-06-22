# mlgw
Api Gateway for Meli

http://localhost:8081/api-docs
http://localhost:8081/swagger-ui-custom.html

http://localhost:8081/actuator/gateway/routes/{id}
http://localhost:8081/actuator/gateway/routes
http://localhost:8081/actuator/gateway/routefilters

POST http://localhost:8081/actuator/gateway/refresh


https://github.com/botorabi/HomieCenter/blob/develop/src/main/java/net/vrfun/homiecenter/reverseproxy/RefreshableRoutesLocator.java

https://stackoverflow.com/questions/50810765/persisting-spring-cloud-gateway-routes-in-database


docker build -t mlgw .

docker image ls

docker-compose up 

./gradlew clean build