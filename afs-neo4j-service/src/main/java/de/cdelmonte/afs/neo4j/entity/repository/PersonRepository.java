package de.cdelmonte.afs.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.afs.neo4j.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByFirstName(String firstName);

  Person findByLastName(String lastName);
}
