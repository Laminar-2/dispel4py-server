FROM gradle:latest as BUILD
WORKDIR /gradle
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build

FROM openjdk:17

COPY --from=BUILD /gradle/build/libs/rest-0.0.1-SNAPSHOT.jar app.jar

ARG MYSQL_USER
ARG MYSQL_PASSWORD
ARG MYSQL_URL
ARG EXECUTION_URL
ARG EXECUTION_PORT

RUN printf "spring.jpa.hibernate.ddl-auto=update\nspring.datasource.url=jdbc:${MYSQL_URL}\nspring.datasource.username=${MYSQL_USER}\nspring.datasource.password=${MYSQL_PASSWORD}\nserver.error.include-message=always\nlaminar.execution.url=http://${EXECUTION_URL}:${EXECUTION_PORT}\nserver.address=0.0.0.0\nspring.servlet.multipart.max-file-size=1000MB\nspring.servlet.multipart.max-request-size=1000MB" > application.properties

ENV server.address 0.0.0.0

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


