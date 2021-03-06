package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class BitcoinAccountEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String address;

  @Relationship(type = "BITCOIN_ACCOUNT_OWNED_BY", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  @SuppressWarnings("unused")
  public BitcoinAccountEntity() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public void withPerson(Person person) {
    if (persons == null) {
      persons = new HashSet<>();
    }
    persons.add(person);
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "BitcoinAccountEntity [id=" + id + ", address=" + address + ", persons=" + persons + "]";
  }
}
