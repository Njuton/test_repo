FROM openjdk:21-jdk-slim

WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

CMD ["sh", "-c", "java -jar $(ls target/java-project-*.jar | head -n 1)"]
