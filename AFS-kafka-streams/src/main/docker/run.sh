#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
     -Dspring.kafka.bootstrap-servers=$KAFKA_BOOTSTRAP_SERVERS \
     -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5006 \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/service/@project.build.finalName@.jar
