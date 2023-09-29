FROM eclipse-temurin:17-jdk-alpine

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

ADD /build/libs/pedidos-0.0.1-SNAPSHOT.jar pedidos-ms.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/pedidos-ms.jar"]