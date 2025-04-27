# RabbitMQ Topic Exchange System with MongoDB Integration

![Java](https://img.shields.io/badge/Java-11-%23ED8B00?logo=openjdk&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-5.15.0-%23FF6600?logo=rabbitmq)
![MongoDB](https://img.shields.io/badge/MongoDB-3.12.12/4.2.0-%2347A248?logo=mongodb)
![gRPC](https://img.shields.io/badge/gRPC-1.15.1-%230052CC?logo=grpc)
![Maven](https://img.shields.io/badge/Maven-3.8.1-%23C71A36?logo=apachemaven)

A high-performance messaging system using RabbitMQ topic exchanges with MongoDB persistence and gRPC support.

## Key Features

- **RabbitMQ Topic Exchange** implementation (amqp-client 5.15.0)
- Multiple specialized consumers with different routing patterns
- **MongoDB integration** with both legacy (3.12.12) and modern (4.2.0) drivers
- **gRPC support** (1.15.1) for high-performance RPC
- Protocol Buffers serialization (protobuf-java 3.6.1)
- Configuration management via external Config.txt

## Technology Stack

### Core Dependencies
- **Messaging**: `com.rabbitmq:amqp-client:5.15.0`
- **Database**: 
  - `org.mongodb:mongo-java-driver:3.12.12`
  - `org.mongodb:mongodb-driver-core:4.2.0`
- **RPC**: 
  - `io.grpc:grpc-*:1.15.1`
  - `com.google.protobuf:protobuf-java:3.6.1`
- **Utilities**: 
  - `javax.annotation:javax.annotation-api:1.2`
  - `jakarta.servlet:jakarta.servlet-api:4.0.2`

## Project Structure


![image](https://github.com/user-attachments/assets/262e0cd7-4aff-426b-b536-239673ec0b0b)

##Advanced Features

    gRPC integration for high-performance service communication

    Protocol Buffers for efficient message serialization


##Development Notes

    The project uses Maven resource filtering for Config.txt

    All RabbitMQ connection management is handled in ConnectionManager.java

    MongoDB DAOs provide data persistence layer


## Build Project
mvn clean install

