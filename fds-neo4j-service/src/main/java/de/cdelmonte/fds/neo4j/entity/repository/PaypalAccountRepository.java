package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.PaypalAccountEntity;

public interface PaypalAccountRepository extends CrudRepository<PaypalAccountEntity, Long> {
  PaypalAccountEntity findByAddress(String address);

}
