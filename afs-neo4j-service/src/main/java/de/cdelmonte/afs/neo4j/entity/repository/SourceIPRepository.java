package de.cdelmonte.afs.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.afs.neo4j.entity.SourceIP;

public interface SourceIPRepository extends CrudRepository<SourceIP, Long> {
  SourceIP findByIp(String ip);
}
