#!/bin/bash

docker rm -fv $(docker ps -qa)
docker volume rm $(docker volume ls -qf dangling=true)
mvn clean package docker:build -Dmaven.test.skip=true
docker-compose -f fds-docker/docker-dev.yml up
