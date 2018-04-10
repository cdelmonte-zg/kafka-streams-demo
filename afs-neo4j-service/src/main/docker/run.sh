#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
     -Dspring.kafka.bootstrap-servers=$KAFKA_BOOTSTRAP_SERVERS \
     -Dspring.data.neo4j.uri=$NEO4J_SVR \
     -Xdebug -Xrunjdwp:server=y,transport=dt_socket,suspend=n,address=5007 \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/service/@project.build.finalName@.jar
