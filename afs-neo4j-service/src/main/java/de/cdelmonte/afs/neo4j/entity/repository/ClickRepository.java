package de.cdelmonte.afs.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.afs.neo4j.entity.Click;

public interface ClickRepository extends CrudRepository<Click, Long> {
  Click findByExtId(Long extId);
}
