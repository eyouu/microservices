# Spring Boot 3.1 + Cloud + Feign + Eureka + Docker 

# Docker

## Images

- https://hub.docker.com/u/eyouu
- Currency Exchange Service 
	- eyouu/microservices-currency-exchange-service:0.0.2-SNAPSHOT
- Currency Conversion Service
	- eyouu/microservices-currency-conversion-service:0.0.2-SNAPSHOT
- Eureka
	- eyouu/microservices-naming-server:0.0.2-SNAPSHOT
- API GATEWAY
	- eyouu/microservices-api-gateway:0.0.2-SNAPSHOT

## URLS

#### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/UAH

#### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/UAH/quantity/10
- http://localhost:8100/currency-conversion-feign/from/USD/to/UAH/quantity/10

#### Eureka
- http://localhost:8761/

#### Zipkin
- http://localhost:9411/

#### API GATEWAY
- http://localhost:8765/currency-exchange/from/USD/to/UAH
- http://localhost:8765/currency-conversion/from/USD/to/UAH/quantity/10
- http://localhost:8765/currency-conversion-feign/from/USD/to/UAH/quantity/10
- http://localhost:8765/currency-conversion-new/from/USD/to/UAH/quantity/10
