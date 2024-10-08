FROM openjdk:17-alpine
ARG JAR_FILE=target/Minesweeper-0.0.1-SNAPSHOT.jar
WORKDIR /opt/app
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","app.jar"]