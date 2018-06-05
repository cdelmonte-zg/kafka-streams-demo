package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.BitcoinAccountEntity;

public interface BitcoinAccountRepository extends CrudRepository<BitcoinAccountEntity, Long> {
  BitcoinAccountEntity findByAddress(String address);
}
