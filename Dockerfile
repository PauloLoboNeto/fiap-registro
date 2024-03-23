FROM maven:3.8.8-eclipse-temurin-17 AS build
COPY .. /root/app/
WORKDIR /root/app
RUN mvn clean package -DskipTests=true

FROM eclipse-temurin:17-jdk-jammy
EXPOSE 9091
COPY --from=build /root/app/ /home/app/
WORKDIR /home/app
ENTRYPOINT ["java", "-jar", "-Xmx1512m", "./target/registro-0.0.1-SNAPSHOT.jar"]

