FROM openjdk:8-jre-alpine 

EXPOSE 8080
EXPOSE 9090
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY target/dependency/BOOT-INF/lib /app/lib
COPY target/dependency/META-INF /app/META-INF
COPY target/dependency/BOOT-INF/classes /app
ENTRYPOINT ["java","-cp","app:app/lib/*","com.doodle.doodle.DoodleApplication"]