#FROM openjdk:11
#COPY target/demo-0.0.1-SNAPSHOT.jar ./temp
#ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
#
##nn

#
#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]


FROM rahmeh21/doist:latest
EXPOSE 8080
COPY target/*.jar  demo-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]
