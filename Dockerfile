FROM debian:buster
RUN apt update && apt upgrade -y && apt install openjdk-11-jre -y
RUN mkdir /bot/
WORKDIR /bot/
COPY build/libs/kotlinautas-bot-all.jar .
CMD ["java", "-jar", "kotlinautas-bot-all.jar"]