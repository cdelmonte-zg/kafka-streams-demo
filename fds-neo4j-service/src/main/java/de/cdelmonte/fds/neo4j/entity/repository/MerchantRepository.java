package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.Merchant;

public interface MerchantRepository extends CrudRepository<Merchant, Long> {
  Merchant findByName(String name);
}
