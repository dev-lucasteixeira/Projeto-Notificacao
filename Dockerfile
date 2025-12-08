FROM gradle:9.2.1-jdk17-alpine AS BUILD
WORKDIR /app
COPY . .
RUN gradle build --no-daemon
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/notificacao.jar
EXPOSE 8083
CMD ["java", "-jar", "/app/notificacao.jar"]