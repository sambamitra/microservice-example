# Microservice Example based on Spring Cloud
This is an example project to demonstrate building micro-services using Spring Boot and Spring Cloud. 
The project demonstrates the following concepts :-

* Microservice development using Spring Boot
* Microservice discovery using Netflix Eureka (Spring Cloud)
* Routing and reverse proxy using Netflix Zuul (Spring Cloud)
* Containerisation of microservices using Docker

## Prerequisites
- JDK 1.8 or later
- Maven 3.x
- Docker - Download and install : <https://www.docker.com/products/overview>

## How to run
* Clone the project
* Build the parent project using : __mvn clean install__
* Go to the __docker__ folder under this project and run the command : __docker compose up__
* Open <http://localhost:8761/> in your browser to see the services registered to Eureka
