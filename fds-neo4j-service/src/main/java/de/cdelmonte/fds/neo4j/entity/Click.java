package de.cdelmonte.fds.neo4j.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

@NodeEntity
public class Click {

  @Id
  @GeneratedValue
  private Long id;

  private Long extId;
  private Date date;
  private String source;

  @Relationship(type = "CLICK_WITH_PERSON", direction = Relationship.UNDIRECTED)
  public Set<Person> persons;

  @SuppressWarnings("unused")
  private Click() {
    // Empty constructor required as of Neo4j API 2.0.5
  };

  public Click(Long extId, Date date, String source) {
    super();
    this.extId = extId;
    this.date = date;
    this.source = source;
  }

  public Long getExtId() {
    return extId;
  }

  public void setExtId(Long extId) {
    this.extId = extId;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public void withPerson(Person person) {
    if (persons == null) {
      persons = new HashSet<>();
    }
    persons.add(person);
  }
}
