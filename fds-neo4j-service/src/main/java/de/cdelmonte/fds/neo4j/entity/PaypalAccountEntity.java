package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class PaypalAccountEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String address;
  private String accountHolder;

  @Relationship(type = "PAYPAL_ACCOUNT_OWNED_BY", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  public PaypalAccountEntity() {};

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

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }
}
