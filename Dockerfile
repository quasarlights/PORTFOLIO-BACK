FROM amazoncorretto:11-alpine-jdk
MAINTAINER MGB
COPY target/api-0.0.1-SNAPSHOT.jar  api-app.jar
ENTRYPOINT ["java","-jar","/api-app.jar"]