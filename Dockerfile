FROM maven:3.8.1-jdk-8-slim AS build
COPY pom.xml /home/app/pom.xml
COPY rmm-services-server-app /home/app/rmm-services-server-app

RUN mvn -f /home/app/pom.xml clean install

FROM openjdk:8-jre-slim
COPY --from=build /home/app/rmm-services-server-app/target/rmm-services-server-app.jar /usr/local/lib/rmm-services-server-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/rmm-services-server-app.jar"]