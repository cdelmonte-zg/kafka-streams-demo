# FDS Playtoy


Data Generator 
--------------------------------------
- creates at the starting point fake users and commits them to the relative Kafka topic
- creates testing data of two kind: normal and suspect transactions
- commits them to the Kafka topic

Kafka Streams
--------------------------------------
- process the incoming streams filtering and mapping them appropriately
- forward the processed streams to their proper outgoing topics

Neo4J Service
--------------------------------------
- Receives the topics related to users and transactions
- Generate and update a Neo4j graph database accordingly to the underlying graphs topology

DAnte
--------------------------------------
- executes Cypher queries to retrieve pertinent informations from the graph database,
in order to recognise given patterns defined as suspicious.

- gives to disposal a rest endpoint to deliver the data to an external service


How to run the FDS Playtoy
--------------------------------------
0. Be sure to have at least java 9 SDK installed on your machine

1. Using the CLI, go in the project root and run the following commands:

```
mvn clean package docker:build -Dmaven.test.skip=true

docker-compose -f fds-docker/docker-dev.yml up -d
```

2. To access to the neo4j browser, go to the URI http://localhost:7474

3. A simple cypher query to visualize some data is:

```
MATCH (n1)-[r]->(n2) RETURN r, n1, n2 LIMIT 25
```

4. To stop the containers run from the project root the command:
```
docker-compose -f fds-docker/docker-dev.yml stop
```

