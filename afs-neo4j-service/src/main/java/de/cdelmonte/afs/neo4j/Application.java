package de.cdelmonte.afs.neo4j;

import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.kafka.annotation.EnableKafka;
import de.cdelmonte.afs.neo4j.entity.Person;
import de.cdelmonte.afs.neo4j.entity.repository.PersonRepository;

@SpringBootApplication
@EnableNeo4jRepositories
@EnableKafka
public class Application {

  private final static Logger log = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) throws Exception {
    SpringApplication.run(Application.class, args);
  }

  @Bean
  CommandLineRunner demo(PersonRepository personRepository) {
    return args -> {

      personRepository.deleteAll();

      Person greg = new Person("Greg", "Torsoln");
      Person roy = new Person("Roy", "Anderson");
      Person craig = new Person("Craig", "McDonald");

      List<Person> team = Arrays.asList(greg, roy, craig);

      log.info("Before linking up with Neo4j...");

      team.stream().forEach(person -> log.info("\t" + person.toString()));

      personRepository.save(greg);
      personRepository.save(roy);
      personRepository.save(craig);

      greg = personRepository.findByFirstName(greg.getFirstName());
      greg.worksWith(roy);
      greg.worksWith(craig);
      personRepository.save(greg);

      roy = personRepository.findByFirstName(roy.getFirstName());
      roy.worksWith(craig);
      // We already know that roy works with greg
      personRepository.save(roy);

      // We already know craig works with roy and greg

      log.info("Lookup each person by name...");
      team.stream().forEach(person -> log
          .info("\t" + personRepository.findByFirstName(person.getFirstName()).toString()));
    };
  }
}
