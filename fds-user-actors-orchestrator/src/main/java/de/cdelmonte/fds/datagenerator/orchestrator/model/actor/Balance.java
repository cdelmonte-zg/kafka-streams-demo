package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.io.Serializable;
import java.util.Date;


public class Balance implements Serializable {
  private static final long serialVersionUID = 1L;
  private long pending;
  private long paid;
  private long denied;
  private long balance;
  private Date lastModified;
  private String currency;

  public long getPending() {
    return pending;
  }

  public void setPending(long pending) {
    this.pending = pending;
  }

  public long getPaid() {
    return paid;
  }

  public void setPaid(long paid) {
    this.paid = paid;
  }

  public long getDenied() {
    return denied;
  }

  public void setDenied(long denied) {
    this.denied = denied;
  }

  public long getBalance() {
    return balance;
  }

  public void setBalance(long balance) {
    this.balance = balance;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + (int) (balance ^ (balance >>> 32));
    result = prime * result + ((currency == null) ? 0 : currency.hashCode());
    result = prime * result + (int) (denied ^ (denied >>> 32));
    result = prime * result + ((lastModified == null) ? 0 : lastModified.hashCode());
    result = prime * result + (int) (paid ^ (paid >>> 32));
    result = prime * result + (int) (pending ^ (pending >>> 32));
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
    if (balance != other.balance)
      return false;
    if (currency == null) {
      if (other.currency != null)
        return false;
    } else if (!currency.equals(other.currency))
      return false;
    if (denied != other.denied)
      return false;
    if (lastModified == null) {
      if (other.lastModified != null)
        return false;
    } else if (!lastModified.equals(other.lastModified))
      return false;
    if (paid != other.paid)
      return false;
    if (pending != other.pending)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "Balance [pending=" + pending + ", paid=" + paid + ", denied=" + denied + ", balance="
        + balance + ", lastModified=" + lastModified + ", currency=" + currency + "]";
  }
}
