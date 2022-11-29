FROM openjdk:11.0.11-oraclelinux7

ARG JAR_FILE=./build/libs/notes-api-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=docker", "-jar","/app.jar"]