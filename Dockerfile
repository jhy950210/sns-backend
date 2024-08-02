FROM amazoncorretto:17 as build
WORKDIR /app
COPY . .
RUN sed -i 's/\r$//' ./gradlew
RUN ./gradlew bootJar

FROM amazoncorretto:17
WORKDIR /app
COPY --from=build /app/build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "/app/app.jar"]
EXPOSE 8080
