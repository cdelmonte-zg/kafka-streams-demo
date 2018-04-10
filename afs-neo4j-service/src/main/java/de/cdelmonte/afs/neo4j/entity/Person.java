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

  private String name;

  private Person() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public Person(String name) {
    this.name = name;
  }

  @Relationship(type = "TEAMMATE", direction = Relationship.UNDIRECTED)
  public Set<Person> teammates;

  public void worksWith(Person person) {
    if (teammates == null) {
      teammates = new HashSet<>();
    }
    teammates.add(person);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String toString() {

    return this.name + "'s teammates => " + Optional.ofNullable(this.teammates)
        .orElse(Collections.emptySet()).stream().map(Person::getName).collect(Collectors.toList());
  }
}
