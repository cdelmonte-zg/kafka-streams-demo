FROM openjdk:9.0.4-jdk-slim

RUN apt-get update && apt-get -y upgrade 
RUN mkdir -p /usr/local/service
ADD @project.build.finalName@.jar /usr/local/service/
ADD run.sh run.sh
RUN chmod +x run.sh
CMD ./run.sh
