package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class CID {
  @Id
  @GeneratedValue
  private Long id;

  private String Cid;


  @Relationship(type = "PERSON_WITH_CID", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  @Relationship(type = "TRANSACTION_WITH_CID", direction = Relationship.UNDIRECTED)
  public Set<TransactionEntity> transactions;


  public String getCid() {
    return Cid;
  }

  public CID() {};

  public void setCid(String cid) {
    Cid = cid;
  }

  public void withPerson(Person person) {
    if (persons == null) {
      persons = new HashSet<>();
    }
    persons.add(person);
  }

  public void withTransaction(TransactionEntity transaction) {
    if (transactions == null) {
      transactions = new HashSet<>();
    }
    transactions.add(transaction);
  }
}
