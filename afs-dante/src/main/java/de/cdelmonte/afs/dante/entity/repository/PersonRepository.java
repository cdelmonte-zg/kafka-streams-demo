package de.cdelmonte.afs.dante.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.afs.dante.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByFirstName(String firstName);

  Person findByLastName(String lastName);

}
