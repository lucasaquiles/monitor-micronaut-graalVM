FROM oracle/graalvm-ce:20.1.0-java8 as graalvm
RUN gu install native-image

COPY . /home/app/monitor
WORKDIR /home/app/monitor

RUN native-image --no-server -cp build/libs/monitor-*-all.jar

FROM frolvlad/alpine-glibc
RUN apk update && apk add libstdc++
EXPOSE 8080
COPY --from=graalvm /home/app/monitor/monitor /app/monitor
ENTRYPOINT ["/app/monitor"]
