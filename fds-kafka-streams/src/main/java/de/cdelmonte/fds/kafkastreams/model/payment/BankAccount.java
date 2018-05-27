package de.cdelmonte.fds.kafkastreams.model.payment;

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

  public void setAccountHolder(String accountHolder) {
    this.accountHolder = accountHolder;
  }

  @Override
  public String toString() {
    return "BankAccount [IBAN=" + IBAN + ", BIC=" + BIC + ", accountHolder=" + accountHolder + "]";
  }
}
