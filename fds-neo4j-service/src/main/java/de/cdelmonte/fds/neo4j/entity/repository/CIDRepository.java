package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.CID;

public interface CIDRepository extends CrudRepository<CID, Long> {
  CID findByCid(String cid);
}
