# kafka-streams-demo


Datagenerator 
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



1. To run go in each directory and execute 

```
mvn -Dmaven.test.skip=true clean package docker:build
```

2. After that, go to the project root and run:

```
docker-compose -f afs-docker-dev/docker-dev.yml up -d
```

3. After a while, go to http:localhost:7474 to access to neo4j

go to http://localhost:2673/swagger-ui.html to access the swagger ui (to be continued...)

