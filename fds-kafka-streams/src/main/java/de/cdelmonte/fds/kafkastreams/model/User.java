package de.cdelmonte.fds.kafkastreams.model;

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
  private Long lastCid;
  private String languages;
  private Date paymentsBlockedTill;

  private boolean emailVerified;
  private boolean paymentsBlocked;
  private boolean blocked;
  private boolean doNotPay;
  private boolean ignoreCountry;
  private boolean automaticPayment;
  private boolean adsEnabled;
  private boolean toolbarUser;
  private boolean mobileAppUser;
  private int numberOfTransactions;

  private Balance balance;
  private Address address;


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
    paymentsBlockedTill = builder.paymentsBlockedTill;

    emailVerified = builder.emailVerified;
    paymentsBlocked = builder.paymentsBlocked;
    blocked = builder.blocked;
    doNotPay = builder.doNotPay;
    ignoreCountry = builder.ignoreCountry;
    automaticPayment = builder.automaticPayment;
    adsEnabled = builder.adsEnabled;
    toolbarUser = builder.toolbarUser;
    mobileAppUser = builder.mobileAppUser;
    numberOfTransactions = builder.numberOfTransactions;

    balance = builder.balance;
    address = builder.address;
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
    builder.setPaymentsBlockedTill(copy.paymentsBlockedTill);

    builder.setEmailVerified(copy.emailVerified);
    builder.setPaymentsBlocked(copy.paymentsBlocked);
    builder.setBlocked(copy.blocked);
    builder.setDoNotPay(copy.doNotPay);
    builder.setIgnoreCountry(copy.ignoreCountry);
    builder.setAutomaticPayment(copy.automaticPayment);
    builder.setAdsEnabled(copy.adsEnabled);
    builder.setToolbarUser(copy.toolbarUser);
    builder.setMobileAppUser(copy.mobileAppUser);
    builder.setNumberOfTransactions(copy.numberOfTransactions);

    builder.setBalance(copy.balance);
    builder.setAddress(copy.address);

    return builder;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((address == null) ? 0 : address.hashCode());
    result = prime * result + (adsEnabled ? 1231 : 1237);
    result = prime * result + (automaticPayment ? 1231 : 1237);
    result = prime * result + ((balance == null) ? 0 : balance.hashCode());
    result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
    result = prime * result + (blocked ? 1231 : 1237);
    result = prime * result + (doNotPay ? 1231 : 1237);
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (emailVerified ? 1231 : 1237);
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + (ignoreCountry ? 1231 : 1237);
    result = prime * result + ((languages == null) ? 0 : languages.hashCode());
    result = prime * result + ((lastCid == null) ? 0 : lastCid.hashCode());
    result = prime * result + ((lastCountry == null) ? 0 : lastCountry.hashCode());
    result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
    result = prime * result + ((lastLoginDate == null) ? 0 : lastLoginDate.hashCode());
    result = prime * result + (mobileAppUser ? 1231 : 1237);
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + numberOfTransactions;
    result = prime * result + (paymentsBlocked ? 1231 : 1237);
    result = prime * result + ((paymentsBlockedTill == null) ? 0 : paymentsBlockedTill.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
    result = prime * result + (toolbarUser ? 1231 : 1237);
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
    if (adsEnabled != other.adsEnabled)
      return false;
    if (automaticPayment != other.automaticPayment)
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
    if (ignoreCountry != other.ignoreCountry)
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
    if (mobileAppUser != other.mobileAppUser)
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
    if (paymentsBlockedTill == null) {
      if (other.paymentsBlockedTill != null)
        return false;
    } else if (!paymentsBlockedTill.equals(other.paymentsBlockedTill))
      return false;
    if (registrationDate == null) {
      if (other.registrationDate != null)
        return false;
    } else if (!registrationDate.equals(other.registrationDate))
      return false;
    if (toolbarUser != other.toolbarUser)
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "User [id=" + id + ", email=" + email + ", username=" + username + ", name=" + name
        + ", birthdate=" + birthdate + ", registrationDate=" + registrationDate + ", lastLoginDate="
        + lastLoginDate + ", lastCountry=" + lastCountry + ", lastIp=" + lastIp + ", lastCid="
        + lastCid + ", languages=" + languages + ", paymentsBlockedTill=" + paymentsBlockedTill
        + ", emailVerified=" + emailVerified + ", paymentsBlocked=" + paymentsBlocked + ", blocked="
        + blocked + ", doNotPay=" + doNotPay + ", ignoreCountry=" + ignoreCountry
        + ", automaticPayment=" + automaticPayment + ", adsEnabled=" + adsEnabled + ", toolbarUser="
        + toolbarUser + ", mobileAppUser=" + mobileAppUser + ", numberOfTransactions="
        + numberOfTransactions + ", balance=" + balance + ", address=" + address + "]";
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
    private Long lastCid;
    private String languages;
    private Date paymentsBlockedTill;

    private boolean emailVerified;
    private boolean paymentsBlocked;
    private boolean blocked;
    private boolean doNotPay;
    private boolean ignoreCountry;
    private boolean automaticPayment;
    private boolean adsEnabled;
    private boolean toolbarUser;
    private boolean mobileAppUser;
    private int numberOfTransactions;

    private Balance balance;
    private Address address;

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

    void setLastCid(Long lastCid) {
      this.lastCid = lastCid;
    }

    void setLanguages(String languages) {
      this.languages = languages;
    }

    void setPaymentsBlockedTill(Date paymentsBlockedTill) {
      this.paymentsBlockedTill = paymentsBlockedTill;
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

    void setIgnoreCountry(boolean ignoreCountry) {
      this.ignoreCountry = ignoreCountry;
    }

    void setAutomaticPayment(boolean automaticPayment) {
      this.automaticPayment = automaticPayment;
    }

    void setAdsEnabled(boolean adsEnabled) {
      this.adsEnabled = adsEnabled;
    }

    void setToolbarUser(boolean toolbarUser) {
      this.toolbarUser = toolbarUser;
    }

    void setMobileAppUser(boolean mobileAppUser) {
      this.mobileAppUser = mobileAppUser;
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

    public User build() {
      return new User(this);
    }
  }
}
