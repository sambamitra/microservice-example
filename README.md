# Microservice Example based on Spring Cloud
This is an example project to demonstrate building micro-services using Spring Boot and Spring Cloud. 
The project demonstrates the following concepts :-

* Microservice development using Spring Boot
* Microservice discovery using Netflix Eureka (Spring Cloud)
* API Gateway for orchestration between microservices
* Routing using Netflix Zuul (Spring Cloud)
* SSL communication between all the services
* API documentation using Swagger
* Correlation between microservices using Spring Cloud Sleuth
* Containerisation of microservices using Docker (WIP)

## Prerequisites
- JDK 1.8 or later
- Maven 3.x
- Docker - Download and install : <https://www.docker.com/products/overview>

## How to run
* Clone the project
* Build the parent project (or individual projects) using : __mvn clean install -DskipDockerBuild__
* Run the components using : __java -jar [name_of_jar].jar__. Run the services in the following order :
 1. discovery
 2. edge
 3. All other individual microservices (book, employee etc.)
 4. api-gateway
* For checking discovery server status, go to <https://localhost:8761>. This should show all the services registered to Eureka.
* For checking individual services, go to __https://localhost:[port]/swagger_ui.html__. This should show the API documentation for the service and you can test out the endpoints.
* For checking the edge server, go to <https://localhost:8765/api/book/available>. This should route the request to the api-gateway and give back the response.

## Architecture diagram
![Microservice Architecture](Microservice Architecture.png)

## Security between services
HTTPS has been configured for the full service stack. The supported SSL version is TLS 1.2.
* Edge Server -> API Gateway - One way SSL
* API Gateway -> Microservices - Two way SSL (mutual authentication)
* Services -> Discovery Server - One way SSL

## Technology choices
The following technology choices have been made.
* Edge Server - Netflix Zuul
* Discovery Server - Netflix Eureka
* API Gateway/Microservices - Spring Boot
* Datastore - MongoDB

## How to run using Docker - WIP
* Clone the project
* Build the parent project using : __mvn clean install__
* Go to the __docker__ folder under this project and run the command : __docker compose up__
* Open <http://localhost:8761/> in your browser to see the services registered to Eureka
