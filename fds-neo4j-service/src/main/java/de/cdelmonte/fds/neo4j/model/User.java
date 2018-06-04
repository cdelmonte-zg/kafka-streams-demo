package de.cdelmonte.fds.neo4j.model;

import java.util.Date;

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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public String getLastCountry() {
    return lastCountry;
  }

  public void setLastCountry(String lastCountry) {
    this.lastCountry = lastCountry;
  }

  public String getLastIp() {
    return lastIp;
  }

  public void setLastIp(String lastIp) {
    this.lastIp = lastIp;
  }

  public String getLastCid() {
    return lastCid;
  }

  public void setLastCid(String lastCid) {
    this.lastCid = lastCid;
  }

  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public boolean isPaymentsBlocked() {
    return paymentsBlocked;
  }

  public void setPaymentsBlocked(boolean paymentsBlocked) {
    this.paymentsBlocked = paymentsBlocked;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public boolean isDoNotPay() {
    return doNotPay;
  }

  public void setDoNotPay(boolean doNotPay) {
    this.doNotPay = doNotPay;
  }

  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

  public void setNumberOfTransactions(int numberOfTransactions) {
    this.numberOfTransactions = numberOfTransactions;
  }

  public Balance getBalance() {
    return balance;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name
        + ", birthdate=" + birthdate + ", registrationDate=" + registrationDate + ", lastLoginDate="
        + lastLoginDate + ", lastCountry=" + lastCountry + ", lastIp=" + lastIp + ", lastCid="
        + lastCid + ", languages=" + languages + ", emailVerified=" + emailVerified
        + ", paymentsBlocked=" + paymentsBlocked + ", blocked=" + blocked + ", doNotPay=" + doNotPay
        + ", numberOfTransactions=" + numberOfTransactions + ", balance=" + balance + ", address="
        + address + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + ((balance == null) ? 0 : balance.hashCode());
    result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
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
    if (birthdate == null) {
      if (other.birthdate != null)
        return false;
    } else if (!birthdate.equals(other.birthdate))
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
}
