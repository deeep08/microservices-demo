# microservices-demo
Sample microservice architecture using
<ul>
    <li>Spring Netflix Eureka</li>
    <li>Spring Netflix Zuul</li>
    <li>Spring Netflix Hystrix</li>
    <li>Spring Security</li>
    <li>Spring Web</li>
    <li>Spring Boot</li>
    <li>JWT</li>
    <li>Ribbon client for load balancing</li>
</ul>

## Services
<ul>
    <li>Eureka - Acts as registry for all the services. Used as a lookup registry by other services to find any service they need.</li>
    <li>Zuul - Acts as the client facing endpoint, responsible for routing request to appropriate services. It also authenticate the user if valid JWT token is present.</li>
    <li>Image Service - A sample user-defined REST-based service which returns images. This service is consumed by Gallery service.</li>
    <li>Gallery Service - A sample user-defined REST-based service which use Ribbon RestTemplate to balance load among running instances of image service. This services uses Hystrix to implement circuit breaker pattern to prevent cascading down the errors.</li>
    <li>Auth Server - A sample authorisation server which authenticate users using Spring Security configuration, generate JWT and return response with JWT token in the response header.</li>
</ul>

## Build all services
Run the below command from project root directory
```
./mvnw clean package
```

## Starting all services
Run the below command from project root directory
```shell script
./start-all.sh
```

A log folder will be created inside project root directory with log files for each service

## Stopping all services
Run the below command from project root directory
```shell script
./stop-all.sh
```
