FROM openjdk:11
VOLUME /tmp
EXPOSE 8090
ADD ./target/springboot-service-aforo255-zuul-server-0.0.1-SNAPSHOT.jar zuul.jar
ENTRYPOINT ["java", "-jar", "/zuul.jar"]