package de.cdelmonte.fds.neo4j.model.payment;

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
}
