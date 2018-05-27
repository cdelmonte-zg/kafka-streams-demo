package de.cdelmonte.fds.neo4j;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableKafka
public class Application {
  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }
}
