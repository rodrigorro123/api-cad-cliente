FROM maven:3.6.3-jdk-11-slim AS build
EXPOSE 8080
ADD /target/api-cad-cliente.jar api-cad-cliente.jar
ENTRYPOINT ["Java","-jar","api-cad-cliente.jar"]

