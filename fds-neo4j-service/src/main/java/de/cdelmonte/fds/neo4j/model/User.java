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

  public Long getLastCid() {
    return lastCid;
  }

  public void setLastCid(Long lastCid) {
    this.lastCid = lastCid;
  }

  public String getLanguages() {
    return languages;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public Date getPaymentsBlockedTill() {
    return paymentsBlockedTill;
  }

  public void setPaymentsBlockedTill(Date paymentsBlockedTill) {
    this.paymentsBlockedTill = paymentsBlockedTill;
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

  public boolean isIgnoreCountry() {
    return ignoreCountry;
  }

  public void setIgnoreCountry(boolean ignoreCountry) {
    this.ignoreCountry = ignoreCountry;
  }

  public boolean isAutomaticPayment() {
    return automaticPayment;
  }

  public void setAutomaticPayment(boolean automaticPayment) {
    this.automaticPayment = automaticPayment;
  }

  public boolean isAdsEnabled() {
    return adsEnabled;
  }

  public void setAdsEnabled(boolean adsEnabled) {
    this.adsEnabled = adsEnabled;
  }

  public boolean isToolbarUser() {
    return toolbarUser;
  }

  public void setToolbarUser(boolean toolbarUser) {
    this.toolbarUser = toolbarUser;
  }

  public boolean isMobileAppUser() {
    return mobileAppUser;
  }

  public void setMobileAppUser(boolean mobileAppUser) {
    this.mobileAppUser = mobileAppUser;
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
        + lastCid + ", languages=" + languages + ", paymentsBlockedTill=" + paymentsBlockedTill
        + ", emailVerified=" + emailVerified + ", paymentsBlocked=" + paymentsBlocked + ", blocked="
        + blocked + ", doNotPay=" + doNotPay + ", ignoreCountry=" + ignoreCountry
        + ", automaticPayment=" + automaticPayment + ", adsEnabled=" + adsEnabled + ", toolbarUser="
        + toolbarUser + ", mobileAppUser=" + mobileAppUser + ", numberOfTransactions="
        + numberOfTransactions + ", balance=" + balance + ", address=" + address + "]";
  }
}
