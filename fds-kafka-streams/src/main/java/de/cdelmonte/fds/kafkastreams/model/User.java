package de.cdelmonte.fds.kafkastreams.model;

import java.util.Date;
import de.cdelmonte.fds.kafkastreams.model.payment.BankAccount;
import de.cdelmonte.fds.kafkastreams.model.payment.BitcoinAccount;
import de.cdelmonte.fds.kafkastreams.model.payment.PaypalAccount;

public class User {
  private Long id;
  private String email;
  private String username;
  private String name;
  private Date birthdate;
  private Date registrationDate;
  private Date lastLoginDate;
  private String lastCountry;
  private String lastIp;
  private String lastCid;
  private String languages;

  private boolean emailVerified;
  private boolean paymentsBlocked;
  private boolean blocked;
  private boolean doNotPay;
  private int numberOfTransactions;

  private Balance balance;
  private Address address;

  private BitcoinAccount bitcoinAccount;
  private PaypalAccount paypalAccount;
  private BankAccount bankAccount;

  private User(Builder builder) {
    id = builder.id;
    email = builder.email;
    username = builder.username;
    name = builder.name;
    birthdate = builder.birthdate;
    registrationDate = builder.registrationDate;
    lastLoginDate = builder.lastLoginDate;
    lastCountry = builder.lastCountry;
    lastIp = builder.lastIp;
    lastCid = builder.lastCid;
    languages = builder.languages;

    emailVerified = builder.emailVerified;
    paymentsBlocked = builder.paymentsBlocked;
    blocked = builder.blocked;
    doNotPay = builder.doNotPay;
    numberOfTransactions = builder.numberOfTransactions;

    balance = builder.balance;
    address = builder.address;

    bitcoinAccount = builder.bitcoinAccount;
    paypalAccount = builder.paypalAccount;
    bankAccount = builder.bankAccount;
  }

  public static Builder builder() {
    return new Builder();
  }

  public static Builder builder(User copy) {
    Builder builder = new Builder();
    builder.setId(copy.id);
    builder.setEmail(copy.email);
    builder.setUsername(copy.username);
    builder.setName(copy.name);
    builder.setBirthdate(copy.birthdate);
    builder.setRegistrationDate(copy.registrationDate);
    builder.setLastLoginDate(copy.lastLoginDate);
    builder.setLastCountry(copy.lastCountry);
    builder.setLastIp(copy.lastIp);
    builder.setLastCid(copy.lastCid);
    builder.setLanguages(copy.languages);

    builder.setEmailVerified(copy.emailVerified);
    builder.setPaymentsBlocked(copy.paymentsBlocked);
    builder.setBlocked(copy.blocked);
    builder.setDoNotPay(copy.doNotPay);
    builder.setNumberOfTransactions(copy.numberOfTransactions);

    builder.setBalance(copy.balance);
    builder.setAddress(copy.address);

    builder.setBankAccount(copy.bankAccount);
    builder.setPaypalAccount(copy.paypalAccount);
    builder.setBitcoinAccount(copy.bitcoinAccount);

    return builder;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name
        + ", birthdate=" + birthdate + ", registrationDate=" + registrationDate + ", lastLoginDate="
        + lastLoginDate + ", lastCountry=" + lastCountry + ", lastIp=" + lastIp + ", lastCid="
        + lastCid + ", languages=" + languages + ", emailVerified=" + emailVerified
        + ", paymentsBlocked=" + paymentsBlocked + ", blocked=" + blocked + ", doNotPay=" + doNotPay
        + ", numberOfTransactions=" + numberOfTransactions + ", balance=" + balance + ", address="
        + address + ", bitcoinAccount=" + bitcoinAccount + ", paypalAccount=" + paypalAccount
        + ", bankAccount=" + bankAccount + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((balance == null) ? 0 : balance.hashCode());
    result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
    result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
    result = prime * result + ((bitcoinAccount == null) ? 0 : bitcoinAccount.hashCode());
    result = prime * result + (blocked ? 1231 : 1237);
    result = prime * result + (doNotPay ? 1231 : 1237);
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (emailVerified ? 1231 : 1237);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((languages == null) ? 0 : languages.hashCode());
    result = prime * result + ((lastCid == null) ? 0 : lastCid.hashCode());
    result = prime * result + ((lastCountry == null) ? 0 : lastCountry.hashCode());
    result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
    result = prime * result + ((lastLoginDate == null) ? 0 : lastLoginDate.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + numberOfTransactions;
    result = prime * result + (paymentsBlocked ? 1231 : 1237);
    result = prime * result + ((paypalAccount == null) ? 0 : paypalAccount.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
    result = prime * result + ((username == null) ? 0 : username.hashCode());
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
    User other = (User) obj;
    if (address == null) {
      if (other.address != null)
        return false;
    } else if (!address.equals(other.address))
      return false;
    if (balance == null) {
      if (other.balance != null)
        return false;
    } else if (!balance.equals(other.balance))
      return false;
    if (bankAccount == null) {
      if (other.bankAccount != null)
        return false;
    } else if (!bankAccount.equals(other.bankAccount))
      return false;
    if (birthdate == null) {
      if (other.birthdate != null)
        return false;
    } else if (!birthdate.equals(other.birthdate))
      return false;
    if (bitcoinAccount == null) {
      if (other.bitcoinAccount != null)
        return false;
    } else if (!bitcoinAccount.equals(other.bitcoinAccount))
      return false;
    if (blocked != other.blocked)
      return false;
    if (doNotPay != other.doNotPay)
      return false;
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (emailVerified != other.emailVerified)
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (languages == null) {
      if (other.languages != null)
        return false;
    } else if (!languages.equals(other.languages))
      return false;
    if (lastCid == null) {
      if (other.lastCid != null)
        return false;
    } else if (!lastCid.equals(other.lastCid))
      return false;
    if (lastCountry == null) {
      if (other.lastCountry != null)
        return false;
    } else if (!lastCountry.equals(other.lastCountry))
      return false;
    if (lastIp == null) {
      if (other.lastIp != null)
        return false;
    } else if (!lastIp.equals(other.lastIp))
      return false;
    if (lastLoginDate == null) {
      if (other.lastLoginDate != null)
        return false;
    } else if (!lastLoginDate.equals(other.lastLoginDate))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (numberOfTransactions != other.numberOfTransactions)
      return false;
    if (paymentsBlocked != other.paymentsBlocked)
      return false;
    if (paypalAccount == null) {
      if (other.paypalAccount != null)
        return false;
    } else if (!paypalAccount.equals(other.paypalAccount))
      return false;
    if (registrationDate == null) {
      if (other.registrationDate != null)
        return false;
    } else if (!registrationDate.equals(other.registrationDate))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

  public static final class Builder {
    private Long id;
    private String email;
    private String username;
    private String name;
    private Date birthdate;
    private Date registrationDate;
    private Date lastLoginDate;
    private String lastCountry;
    private String lastIp;
    private String lastCid;
    private String languages;

    private boolean emailVerified;
    private boolean paymentsBlocked;
    private boolean blocked;
    private boolean doNotPay;
    private int numberOfTransactions;

    private Balance balance;
    private Address address;

    private BitcoinAccount bitcoinAccount;
    private PaypalAccount paypalAccount;
    private BankAccount bankAccount;

    private void setId(Long id) {
      this.id = id;
    }

    void setEmail(String email) {
      this.email = email;
    }

    void setUsername(String username) {
      this.username = username;
    }

    void setName(String name) {
      this.name = name;
    }

    void setBirthdate(Date birthdate) {
      this.birthdate = birthdate;
    }

    void setRegistrationDate(Date registrationDate) {
      this.registrationDate = registrationDate;
    }

    void setLastLoginDate(Date lastLoginDate) {
      this.lastLoginDate = lastLoginDate;
    }

    void setLastCountry(String lastCountry) {
      this.lastCountry = lastCountry;
    }

    void setLastIp(String lastIp) {
      this.lastIp = lastIp;
    }

    void setLastCid(String lastCid) {
      this.lastCid = lastCid;
    }

    void setLanguages(String languages) {
      this.languages = languages;
    }

    void setEmailVerified(boolean emailVerified) {
      this.emailVerified = emailVerified;
    }

    void setPaymentsBlocked(boolean paymentsBlocked) {
      this.paymentsBlocked = paymentsBlocked;
    }

    void setBlocked(boolean blocked) {
      this.blocked = blocked;
    }

    void setDoNotPay(boolean doNotPay) {
      this.doNotPay = doNotPay;
    }

    void setNumberOfTransactions(int numberOfTransactions) {
      this.numberOfTransactions = numberOfTransactions;
    }

    void setBalance(Balance balance) {
      this.balance = balance;
    }

    void setAddress(Address address) {
      this.address = address;
    }

    public void setBitcoinAccount(BitcoinAccount bitcoinAccount) {
      this.bitcoinAccount = bitcoinAccount;
    }

    public void setPaypalAccount(PaypalAccount paypalAccount) {
      this.paypalAccount = paypalAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
      this.bankAccount = bankAccount;
    }

    public User build() {
      return new User(this);
    }
  }
}
