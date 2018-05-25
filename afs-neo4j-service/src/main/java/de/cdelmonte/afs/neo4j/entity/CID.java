package de.cdelmonte.afs.neo4j.entity;

import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class CID {
  @Id
  @GeneratedValue
  private Long id;

  private String Cid;

  public String getCid() {
    return Cid;
  }

  @SuppressWarnings("unused")
  private CID() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public void setCid(String cid) {
    Cid = cid;
  }

  public CID(String cid) {
    super();
    Cid = cid;
  }
}
