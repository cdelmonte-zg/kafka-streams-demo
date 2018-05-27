package de.cdelmonte.fds.dante.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.dante.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByFirstName(String firstName);

  Person findByLastName(String lastName);

}
