# ```
# singularity\singularity-gw>  docker build -t singularity-gw:1.0.0 -f Dockerfiles/Dockerfile .
# ```
FROM openjdk:17-jdk-slim
LABEL name="rhslrhs"

ENV TZ=Asia/Seoul

RUN echo 'export PATH=/usr/local/openjdk-17/bin:$PATH' >> ~/.bashrc

EXPOSE 11100

COPY build/libs/singularity-gw-0.0.1-SNAPSHOT.jar /var/apps/singularity-gw-0.0.1-SNAPSHOT.jar

CMD ["java", "-jar", "-Dserver.port=11100", "-Dspring.profiles.active=rhslpc", "/var/apps/singularity-gw-0.0.1-SNAPSHOT.jar"]