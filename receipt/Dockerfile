FROM gradle:jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17.0.1-jdk-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/
EXPOSE 8080
ENTRYPOINT ["java", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/receipt.jar"]