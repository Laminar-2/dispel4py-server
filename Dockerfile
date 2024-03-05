FROM gradle:latest as BUILD
WORKDIR /gradle
COPY . .
RUN chmod +x ./gradlew
RUN ./gradlew build

FROM openjdk:17-jdk-alpine

COPY --from=BUILD /gradle/build/libs/rest-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]


