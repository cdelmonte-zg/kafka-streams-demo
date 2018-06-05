package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.BankAccountEntity;

public interface BankAccountRepository extends CrudRepository<BankAccountEntity, Long> {
  BankAccountEntity findByIBAN(String IBAN);
}
