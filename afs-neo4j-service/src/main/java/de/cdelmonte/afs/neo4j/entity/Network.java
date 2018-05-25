package de.cdelmonte.afs.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Network {
  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @SuppressWarnings("unused")
  private Network() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Network(String name) {
    super();
    this.name = name;
  }
}
