package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.AddressEntity;

public interface AddressRepository extends CrudRepository<AddressEntity, Long> {


}
