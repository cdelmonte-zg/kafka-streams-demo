package de.cdelmonte.afs.datagenerator.model;

public class User {

  private Long id;
  private Long groupId;
  private String email;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getGroupId() {
    return groupId;
  }

  public void setGroupId(Long groupId) {
    this.groupId = groupId;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", groupId=" + groupId + ", email=" + email + "]";
  }
}
