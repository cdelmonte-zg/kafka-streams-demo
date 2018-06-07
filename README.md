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


--------------------------------------

1. To run go in the project root and run 

```
mvn clean package docker:build -Dmaven.test.skip=true

docker-compose -f fds-docker-dev/docker-dev.yml up -d
```

--------------------------------------
to access to neo4j browser: http://localhost:7474

