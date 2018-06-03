# kafka-streams-demo


1) Datagenerator 
--------------------------------------
- creates at the starting point fake users and commits them to the relative Kafka topic
- creates testing data of two kind: normal and suspect transactions
- commits them to the Kafka topic

2) Kafka Streams
--------------------------------------
- process the incoming streams filtering and mapping them appropriately
- forward the processed streams to their proper outgoing topics

3) Neo4J Service
--------------------------------------
- Receives the topics related to users and transactions
- Generate and update a Neo4j graph database accordingly to the underlying graphs topology

4) DAnte
--------------------------------------
- executes Cypher queries to retrieve pertinent informations from the graph database,
in order to recognise given patterns defined as suspicious.

- gives to disposal a rest endpoint to deliver the data to an external service




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


go to http://localhost:2673/swagger-ui.html to access the swagger ui