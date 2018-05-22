package de.cdelmonte.afs.datagenerator.model.payment;

import java.io.Serializable;

public class PaypalAccount extends PaymentAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String address;
  private String accountHolder;

  public PaypalAccount() {}

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
    result = prime * result + ((address == null) ? 0 : address.hashCode());
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
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "PaypalAccount [address=" + address + ", accountHolder=" + accountHolder + "]";
  }
}
