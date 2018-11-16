package de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment;

import java.io.Serializable;


public class PaypalAccount extends PaymentAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String email;
  private boolean verified;
  private String accountHolder;

  public PaypalAccount() {}

  public String getEmail() {
    return email;
  }

  public PaypalAccount setEmail(String email) {
    this.email = email;

    return this;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public PaypalAccount setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
    return this;
  }

  public boolean isVerified() {
    return verified;
  }

  public void setVerified(boolean verified) {
    this.verified = verified;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (verified ? 1231 : 1237);
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
    PaypalAccount other = (PaypalAccount) obj;
    if (accountHolder == null) {
      if (other.accountHolder != null)
        return false;
    } else if (!accountHolder.equals(other.accountHolder))
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (verified != other.verified)
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PaypalAccount [email=" + email + ", verified=" + verified + ", accountHolder="
        + accountHolder + "]";
  }
}
