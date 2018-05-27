package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByIdExt(Long userId);

}
