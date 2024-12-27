FROM openjdk:11

RUN apt-get update && apt-get install -y wget

RUN wget https://raw.githubusercontent.com/vishnubob/wait-for-it/master/wait-for-it.sh -O /wait-for-it.sh
RUN chmod +x /wait-for-it.sh

VOLUME /tmp

COPY target/Musicas-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["/wait-for-it.sh", "musicabd:3306", "--timeout=60", "--", "java", "-jar", "/app.jar"]
