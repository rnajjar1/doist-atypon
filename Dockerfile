FROM openjdk:11
COPY target/demo-0.0.1-SNAPSHOT.jar ./temp
ENTRYPOINT ["java","-jar","demo-0.0.1-SNAPSHOT.jar"]




#FROM openjdk:8-jdk-alpine
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
