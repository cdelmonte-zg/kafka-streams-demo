package de.cdelmonte.fds.datagenerator.model.payment;

import java.io.Serializable;

public class BankAccount extends PaymentAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String IBAN;
  private String BIC;
  private String accountHolder;

  public BankAccount() {}

  public String getIBAN() {
    return IBAN;
  }

  public void setIBAN(String iBAN) {
    IBAN = iBAN;
  }

  public String getBIC() {
    return BIC;
  }

  public void setBIC(String bIC) {
    BIC = bIC;
  }

  public String getAccountHolder() {
    return accountHolder;
  }

  public BankAccount setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
    return this;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((BIC == null) ? 0 : BIC.hashCode());
    result = prime * result + ((IBAN == null) ? 0 : IBAN.hashCode());
    result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
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
    BankAccount other = (BankAccount) obj;
    if (BIC == null) {
      if (other.BIC != null)
        return false;
    } else if (!BIC.equals(other.BIC))
      return false;
    if (IBAN == null) {
      if (other.IBAN != null)
        return false;
    } else if (!IBAN.equals(other.IBAN))
      return false;
    if (accountHolder == null) {
      if (other.accountHolder != null)
        return false;
    } else if (!accountHolder.equals(other.accountHolder))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BankAccount [IBAN=" + IBAN + ", BIC=" + BIC + ", accountHolder=" + accountHolder + "]";
  }
}
