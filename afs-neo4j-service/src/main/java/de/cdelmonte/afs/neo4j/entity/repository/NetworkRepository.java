package de.cdelmonte.afs.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.afs.neo4j.entity.Network;

public interface NetworkRepository extends CrudRepository<Network, Long> {
  Network findByName(String name);
}
