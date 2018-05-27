package de.cdelmonte.fds.neo4j.model.payment;

import java.io.Serializable;

public class BitcoinAccount extends PaymentAccount implements Serializable {
  private static final long serialVersionUID = 1L;

  private String address;

  public BitcoinAccount() {}

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "BitcoinAccount [address=" + address + "]";
  }
}
