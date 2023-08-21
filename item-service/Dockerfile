FROM bellsoft/liberica-openjdk-alpine-musl:17

WORKDIR /opt

COPY build/libs/*.jar item-service.jar

ADD https://github.com/open-telemetry/opentelemetry-java-instrumentation/releases/latest/download/opentelemetry-javaagent.jar .

ENV JAVA_TOOL_OPTIONS "-javaagent:./opentelemetry-javaagent.jar -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"

CMD ["java","-jar","item-service.jar"]