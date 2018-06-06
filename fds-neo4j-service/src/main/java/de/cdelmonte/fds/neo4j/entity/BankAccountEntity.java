package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class BankAccountEntity {
  @Id
  @GeneratedValue
  private Long id;

  private String iban;
  private String bic;
  private String accountHolder;

  @Relationship(type = "BANK_ACCOUNT_OWNED_BY", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  @SuppressWarnings("unused")
  public BankAccountEntity() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getBic() {
    return bic;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  public void withPerson(Person person) {
    if (persons == null) {
      persons = new HashSet<>();
    }
    persons.add(person);
  }

  @Override
  public String toString() {
    return "BankAccountEntity [id=" + id + ", iban=" + iban + ", bic=" + bic + ", accountHolder="
        + accountHolder + ", persons=" + persons + "]";
  }
}
