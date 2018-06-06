package de.cdelmonte.fds.neo4j.model;

public class Balance {
  private Long pending;
  private Long paid;
  private Long denied;
  private Long received;

  public Long getPending() {
    return pending;
  }

  public void setPending(Long pending) {
    this.pending = pending;
  }

  public Long getPaid() {
    return paid;
  }

  public void setPaid(Long paid) {
    this.paid = paid;
  }

  public Long getDenied() {
    return denied;
  }

  public void setDenied(Long denied) {
    this.denied = denied;
  }

  public Long getReceived() {
    return received;
  }

  public void setReceived(Long received) {
    this.received = received;
  }
}
