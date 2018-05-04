package de.cdelmonte.afs.dante.entity.repository;

import java.util.List;
import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import de.cdelmonte.afs.dante.entity.Transaction;

@Component
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

  @Query("MATCH (n1:Transaction) RETURN n1 LIMIT 25")
  List<Transaction> getAllTransactions();
}
