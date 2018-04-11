package de.cdelmonte.afs.neo4j.entity;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  private String firstName;
  private String lastName;

  @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
  public Set<Person> teammates;

  @Relationship(type = "CREDITCARD", direction = Relationship.UNDIRECTED)
  public Set<CreditCard> creditCards;

  @Relationship(type = "TRANSACTION", direction = Relationship.UNDIRECTED)
  public Set<Transaction> transactions;

  @SuppressWarnings("unused")
  private Person() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public Person(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public void worksWith(Person person) {
    if (teammates == null) {
      teammates = new HashSet<>();
    }
    teammates.add(person);
  }

  public void hasCreditCard(CreditCard creditCard) {
    if (creditCards == null) {
      creditCards = new HashSet<>();
    }
    creditCards.add(creditCard);
  }

  public void withTransaction(Transaction transaction) {
    if (transactions == null) {
      transactions = new HashSet<>();
    }
    transactions.add(transaction);
  }

  public String getFirstName() {
    return firstName;
  }

  public String getFullName() {
    return firstName + " " + lastName;
  }


  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String toString() {
    return this.getFullName() + "'s teammates => "
        + Optional.ofNullable(this.teammates).orElse(Collections.emptySet()).stream()
            .map(Person::getFullName).collect(Collectors.toList());
  }
}
