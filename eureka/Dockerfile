FROM bellsoft/liberica-openjdk-alpine-musl:17

WORKDIR /opt

COPY build/libs/*.jar eureka.jar

CMD ["java","-jar","eureka.jar"]
