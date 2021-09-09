FROM openjdk:8
EXPOSE 8080
ADD target/doist.jar doist.jar
ENTRYPOINT ["java","-jar","/doist.jar"]