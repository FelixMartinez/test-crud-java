# Microservices Project

## Description

This project is a collection of microservices developed with Spring Boot, managing client information and account movements. It uses Eureka for service registration, RabbitMQ for messaging, and MySQL as the database.

## Project Structure

- `eureka-server`: Eureka server for service registration and discovery.
- `servicio-cliente-persona`: Microservice for managing client information.
- `servicio-cuenta-movimientos`: Microservice for managing account movements.
- `zull-service`: API Gateway using Spring Cloud Gateway.
- `db`: Contains the database initialization script and Dockerfile.
- `postman`: Contains Postman collections for testing the APIs.
- `docker-compose.yml`: Docker Compose file to run all services.

## Prerequisites

- Java 17
- Maven 3.6+
- Docker
- Docker Compose (optional but recommended)

## Setup

### Environment Variables

Make sure to configure the following environment variables for the microservices to function correctly:

- `SPRING_DATASOURCE_URL`
- `SPRING_DATASOURCE_USERNAME`
- `SPRING_DATASOURCE_PASSWORD`
- `EUREKA_CLIENT_SERVICEURL_DEFAULTZONE`
- `SPRING_RABBITMQ_HOST`
- `SPRING_RABBITMQ_PORT`
- `SPRING_RABBITMQ_USERNAME`
- `SPRING_RABBITMQ_PASSWORD`

### Configuration Files

Each microservice has its own `application.properties` file located in its `src/main/resources` directory.
Each microservice test has its own `application-test.properties` file located in its `src/main/resources` directory.

## Running the Project

### With Docker

Make sure you have Docker and Docker Compose installed.

1. Create a Docker network for the services:
    docker network create microservices

2. Start the Eureka, RabbitMQ, and MySQL containers:
    docker-compose up -d

3. Build and run the microservices:
    mvn clean install

### Without Docker

1. Make sure you have MySQL and RabbitMQ installed and running.
2. Configure the connection properties in `application.properties` for each microservice.
3. Run each microservice:
    mvn clean install
    mvn spring-boot:run

## Endpoints

### ServicioCuentaMovimientos

- `GET /cuentas`: Get all accounts.
- `GET /cuentas/{id}`: Get an account by ID.
- `POST /cuentas`: Create a new account.
- `PUT /cuentas/{id}`: Update an existing account.
- `DELETE /cuentas/{id}`: Delete an account.

### ServicioClientePersona

- `GET /clientes`: Get all clients.
- `GET /clientes/{id}`: Get a client by ID.
- `POST /clientes`: Create a new client.
- `PUT /clientes/{id}`: Update an existing client.
- `DELETE /clientes/{id}`: Delete a client.

## Database Setup

### Generate and Run Database Script

The `BaseDatos.sql` file is located in the `db` directory and contains the table definitions and initial data.

To run the script in MySQL, use the following command:
   docker exec -i mysql_container_name mysql -u user -ppassword mi_db < db/BaseDatos.sql

## Postman Collection

The `postman` directory contains Postman collections for testing the APIs. Import these collections into Postman to test the endpoints.

## Contributing

If you want to contribute to this project, please follow these steps:

1. Fork the project.
2. Create a new branch (`git checkout -b feature/new-feature`).
3. Make your changes and commit them (`git commit -am 'Add new feature'`).
4. Push your changes to the branch (`git push origin feature/new-feature`).
5. Create a new Pull Request.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.
