FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/*.jar message-service-1.0.jar
ENTRYPOINT ["java","-jar","/message-service-1.0.jar"]