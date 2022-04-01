FROM gradle:7.4.1-jdk17 as builder

WORKDIR /usr/src
COPY ["build.gradle", "gradlew", "settings.gradle", "./"]
COPY gradle ./gradle
COPY src ./src

RUN set -eux && gradle bootJar

FROM openjdk:17
WORKDIR /usr/src
COPY --from=builder /usr/src/build/libs/*.jar ./application.jar
ENV CLASSPATH ./application.jar
EXPOSE 8080

CMD ["java", "-jar", "application.jar"]