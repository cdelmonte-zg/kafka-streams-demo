package de.cdelmonte.fds.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Merchant {

  @Id
  @GeneratedValue
  private Long id;

  private Long idExt;
  private String name;

  @SuppressWarnings("unused")
  public Merchant() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public Merchant(Long idExt, String name) {
    super();
    this.idExt = idExt;
    this.name = name;
  }

  public Long getIdExt() {
    return idExt;
  }

  public void setIdExt(Long idExt) {
    this.idExt = idExt;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Merchant [id=" + id + ", idExt=" + idExt + ", name=" + name + "]";
  }
}
