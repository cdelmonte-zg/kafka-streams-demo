package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class SourceIP {

  @Id
  @GeneratedValue
  Long id;
  String ip;

  public SourceIP(String ip) {
    this.ip = ip;
  }

  @Relationship(type = "SOURCE_WITH_PERSON", direction = Relationship.UNDIRECTED)
  Set<Person> persons;

  @Relationship(type = "SOURCE_WITH_CLICK", direction = Relationship.UNDIRECTED)
  Set<Click> clicks;

  @Relationship(type = "SOURCE_WITH_TRANSACTION", direction = Relationship.UNDIRECTED)
  Set<TransactionEntity> transactions;

  @SuppressWarnings("unused")
  private SourceIP() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public void withClick(Click click) {
    if (clicks == null) {
      clicks = new HashSet<>();
    }
    clicks.add(click);
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
