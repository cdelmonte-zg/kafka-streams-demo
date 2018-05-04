package de.cdelmonte.afs.dante.entity.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import de.cdelmonte.afs.dante.entity.Transaction;

@Component
public interface CreditCardRepository extends CrudRepository<Transaction, Long> {

}
