package de.cdelmonte.fds.kafkastreams.model.payment;

import java.io.Serializable;

public class BankAccount extends PaymentAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String iban;
  private String bic;
  private String accountHolder;

  public BankAccount() {}

  public String getAccountHolder() {
    return accountHolder;
  }

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  public String getIban() {
    return iban;
  }

  public void setIban(String iban) {
    this.iban = iban;
  }

  public String getBic() {
    return bic;
  }

  public void setBic(String bic) {
    this.bic = bic;
  }

  @Override
  public String toString() {
    return "BankAccount [iban=" + iban + ", bic=" + bic + ", accountHolder=" + accountHolder + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountHolder == null) ? 0 : accountHolder.hashCode());
    result = prime * result + ((bic == null) ? 0 : bic.hashCode());
    result = prime * result + ((iban == null) ? 0 : iban.hashCode());
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
    if (accountHolder == null) {
      if (other.accountHolder != null)
        return false;
    } else if (!accountHolder.equals(other.accountHolder))
      return false;
    if (bic == null) {
      if (other.bic != null)
        return false;
    } else if (!bic.equals(other.bic))
      return false;
    if (iban == null) {
      if (other.iban != null)
        return false;
    } else if (!iban.equals(other.iban))
      return false;
    return true;
  }
}
