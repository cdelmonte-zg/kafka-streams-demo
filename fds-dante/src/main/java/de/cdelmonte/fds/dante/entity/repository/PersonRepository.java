package de.cdelmonte.fds.dante.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.dante.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByFirstName(String firstName);

  Person findByLastName(String lastName);

  // MATCH (n1:SourceIP)-[r1]-(n2)-[r2]-(n3:SourceIP) RETURN r1, r2, n1, n2,n3

}
