package de.cdelmonte.fds.neo4j.entity;

import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class AddressEntity {

  @Id
  @GeneratedValue
  private Long id;

  private String country;
  private String state = "";
  private String city;
  private String zipCode;
  private String streetAddress;
  private String streetNumber;


  @Relationship(type = "ADDRESS_WITH_PERSON", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  @SuppressWarnings("unused")
  public AddressEntity() {
    // Empty constructor required as of Neo4j API 2.0.5
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getZipCode() {
    return zipCode;
  }

  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  public String getStreetAddress() {
    return streetAddress;
  }

  public void setStreetAddress(String streetAddress) {
    this.streetAddress = streetAddress;
  }

  public String getStreetNumber() {
    return streetNumber;
  }

  public void setStreetNumber(String streetNumber) {
    this.streetNumber = streetNumber;
  };

  public void withPerson(Person person) {
    if (persons == null) {
      persons = new HashSet<>();
    }
    persons.add(person);
  }
}
