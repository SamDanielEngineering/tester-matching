#
# Build stage
#
FROM maven:3.8.6-openjdk-18 AS MAVEN_TOOL_CHAIN
COPY src ./src
COPY pom.xml ./
RUN mvn package

# define base docker image
FROM openjdk:18
LABEL maintainer="Sam Daniel"
COPY --from=MAVEN_TOOL_CHAIN target/tester-matching-service-0.0.1-SNAPSHOT.jar tester-matching-service.jar
ENTRYPOINT ["java", "-jar", "tester-matching-service.jar"]
EXPOSE 8080