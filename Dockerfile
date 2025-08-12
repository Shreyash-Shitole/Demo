# ===== Build Stage =====
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -q -DskipTests clean package

# ===== Run Stage =====
FROM eclipse-temurin:17-jre
WORKDIR /app
ENV SPRING_DATA_MONGODB_URI="mongodb://host.docker.internal:27017" \
    SPRING_DATA_MONGODB_DATABASE="global" \
    SPRING_MAIL_USERNAME="your_gmail@gmail.com" \
    SPRING_MAIL_PASSWORD="your_app_password"
COPY --from=build /app/target/score-sizzle-1.0.0.jar app.jar
EXPOSE 3030
ENTRYPOINT ["java", "-jar", "app.jar"]