# kafka-streams-demo

To run go in each directory and execute 

```
mvn clean package docker:build
```

After that, go to the project root and run:

```
docker-compose -f afs-docker-dev/docker-dev.yml up -d
```

go to http:localhost:7474 and set the new access data for neo4j to:

username: neo4j
password: secret
