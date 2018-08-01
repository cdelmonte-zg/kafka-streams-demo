package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((denied == null) ? 0 : denied.hashCode());
    result = prime * result + ((paid == null) ? 0 : paid.hashCode());
    result = prime * result + ((pending == null) ? 0 : pending.hashCode());
    result = prime * result + ((received == null) ? 0 : received.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Balance other = (Balance) obj;
    if (denied == null) {
      if (other.denied != null)
        return false;
    } else if (!denied.equals(other.denied))
      return false;
    if (paid == null) {
      if (other.paid != null)
        return false;
    } else if (!paid.equals(other.paid))
      return false;
    if (pending == null) {
      if (other.pending != null)
        return false;
    } else if (!pending.equals(other.pending))
      return false;
    if (received == null) {
      if (other.received != null)
        return false;
    } else if (!received.equals(other.received))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Balance [pending=" + pending + ", paid=" + paid + ", denied=" + denied + ", received="
        + received + "]";
  }
}
