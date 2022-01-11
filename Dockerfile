FROM adoptopenjdk/openjdk11:alpine
ARG JAR_FILE

COPY ./target/${JAR_FILE} app.jar

EXPOSE 8090

ENTRYPOINT ["java","-jar","-Duser.timezone=America/Sao_Paulo","/app.jar"]
