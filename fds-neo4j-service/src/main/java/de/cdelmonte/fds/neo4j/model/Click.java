package de.cdelmonte.fds.neo4j.model;

import java.util.Date;

public class Click extends Mock {
  private Long id;
  private Date date;
  private String ip;
  private String source;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getDate() {
    return date;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public String getIp() {
    return ip;

  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
}
