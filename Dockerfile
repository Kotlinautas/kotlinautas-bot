FROM alpine:3.14
RUN apk update && apk add openjdk11
RUN mkdir /bot/
WORKDIR /bot/
COPY build/libs/kotlinautas-bot-all.jar .
CMD ["java", "-jar", "kotlinautas-bot-all.jar"]