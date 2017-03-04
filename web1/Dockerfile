FROM ubuntu:16.04

COPY target/web.jar /

RUN apt-get update -y && \
    apt-get install default-jdk -y -f

EXPOSE 8080
CMD ["java", "-jar", "web.jar"]