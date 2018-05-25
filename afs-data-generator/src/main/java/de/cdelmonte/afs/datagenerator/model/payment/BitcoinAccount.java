package de.cdelmonte.afs.datagenerator.model.payment;

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
  public int hashCode() {
    final int prime = 31;
    int result = 1;
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
    BitcoinAccount other = (BitcoinAccount) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "BitcoinAccount [address=" + address + "]";
  }
}
