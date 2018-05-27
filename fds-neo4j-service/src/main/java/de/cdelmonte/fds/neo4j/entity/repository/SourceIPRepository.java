package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.SourceIP;

public interface SourceIPRepository extends CrudRepository<SourceIP, Long> {
  SourceIP findByIp(String ip);
}
