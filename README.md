# Koerber Microservices Assignment

Spring Boot microservices implementation containing Inventory and Order
services. The project demonstrates clean architecture, REST
communication between services, Factory Pattern usage, proper exception
handling, and unit testing.

------------------------------------------------------------------------

## Tech Stack

-   Java 17
-   Spring Boot
-   Spring Data JPA
-   H2 In-Memory Database
-   Maven
-   JUnit 5 / Mockito

------------------------------------------------------------------------

## Project Structure

koerber-microservices-assignment ├── inventory-service └── order-service

------------------------------------------------------------------------

# Inventory Service

Port: 8081

## Run

cd inventory-service\
mvn spring-boot:run

## APIs

### Get Inventory

GET http://localhost:8081/inventory/{productId}

### Update Inventory

POST http://localhost:8081/inventory/update

Example error response:

{ "timestamp": "...", "error": "Not enough stock available" }

------------------------------------------------------------------------

# Order Service

Port: 8082

## Run

cd order-service\
mvn spring-boot:run

## API

### Place Order

POST http://localhost:8082/order

Request Body:

{ "productId": 1001, "quantity": 5 }

------------------------------------------------------------------------

# Design Highlights

-   Layered architecture (Controller → Service → Repository)
-   Factory Pattern for inventory strategy handling
-   Global exception handling
-   DTO-based request/response handling
-   REST communication between services
-   Unit tests for controller and service layers
-   H2 database configuration for development

------------------------------------------------------------------------

# Testing

Run all tests:

mvn clean test

------------------------------------------------------------------------

# Notes

-   Both services run independently.
-   Order service communicates with Inventory service via REST.
-   H2 console is enabled for development and testing.
