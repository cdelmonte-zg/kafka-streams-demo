package de.cdelmonte.afs.neo4j.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import de.cdelmonte.afs.neo4j.entity.Transaction;

@Component
public interface CreditCardRepository extends CrudRepository<Transaction, Long> {

}
