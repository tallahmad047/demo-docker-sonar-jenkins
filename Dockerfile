FROM openjdk:17-jdk-alpine
LABEL maintainer="tall@test.com"
VOLUME /main-app
ADD target/demo-docker-sonar-jenkins-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085
# java -jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]