package de.cdelmonte.fds.kafkastreams.model.payment;

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
}
