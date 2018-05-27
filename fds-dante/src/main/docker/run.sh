#!/bin/sh

java -Djava.security.egd=file:/dev/./urandom -Dserver.port=$SERVER_PORT \
     -Deureka.client.serviceUrl.defaultZone=$EUREKASERVER_URI \
     -Dspring.data.neo4j.uri=$NEO4J_SVR \
     -Dspring.profiles.active=$PROFILE -jar /usr/local/service/@project.build.finalName@.jar
