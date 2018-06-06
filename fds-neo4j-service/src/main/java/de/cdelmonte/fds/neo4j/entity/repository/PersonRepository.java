package de.cdelmonte.fds.neo4j.entity.repository;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.repository.CrudRepository;
import de.cdelmonte.fds.neo4j.entity.Person;

public interface PersonRepository extends CrudRepository<Person, Long> {

  Person findByIdExt(Long userId);

  @Query("MATCH (p1:Person)-[r1]-(n:PaypalAccountEntity)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getPaypalInCommonWithHowManyPersons(Long id);

  @Query("MATCH (p1:Person)-[r1]-(n:AddressEntity)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getAdressInCommonWithHowManyPersons(Long id);

  @Query("MATCH (p1:Person)-[r1]-(n:CID)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getCidInCommonWithHowManyPersons(Long id);

  @Query("MATCH (p1:Person)-[r1]-(n:SourceIP)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getSourceIPInCommonWithHowManyPersons(Long id);

  @Query("MATCH (p1:Person)-[r1]-(n:BankAccountEntity)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getBankAccountInCommonWithHowManyPersons(Long id);

  @Query("MATCH (p1:Person)-[r1]-(n:BitcoinAccountEntity)-[r2]-(p2:Person) WHERE p1.idExt = {0} RETURN COUNT(p2)")
  int getBitcoinAccountInCommonWithHowManyPersons(Long id);
}
