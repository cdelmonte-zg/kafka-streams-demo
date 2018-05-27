#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
     -Dspring.kafka.bootstrap-servers=$KAFKA_BOOTSTRAP_SERVERS \
     -Dscheduler.produceUsers.fixed-rate.milliseconds=$FIXED_RATE_MILLISECONDS \
     -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5005 \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/service/@project.build.finalName@.jar
