package de.cdelmonte.fds.neo4j.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;


@NodeEntity
public class Person {

  @Id
  @GeneratedValue
  private Long id;

  private Long idExt;
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

  // private Balance balance;
  // private Address address;


  @Relationship(type = "PERSON_WITH_TRANSACTION", direction = Relationship.UNDIRECTED)
  public Set<TransactionEntity> transactions;

  @Relationship(type = "PERSON_WITH_MERCHANT", direction = Relationship.UNDIRECTED)
  public Set<Merchant> merchants;

  @SuppressWarnings("unused")
  private Person() {
    // Empty constructor required as of Neo4j API 2.0.5
  };



  public Person(Long idExt, String email, String username, String name, Date birthdate,
      Date registrationDate, Date lastLoginDate, String lastCountry, String lastIp, Long lastCid,
      String languages, Date paymentsBlockedTill, boolean emailVerified, boolean paymentsBlocked,
      boolean blocked, boolean doNotPay, boolean ignoreCountry, boolean automaticPayment,
      boolean adsEnabled, boolean toolbarUser, boolean mobileAppUser, int numberOfTransactions) {
    super();
    this.idExt = idExt;
    this.email = email;
    this.username = username;
    this.name = name;
    this.birthdate = birthdate;
    this.registrationDate = registrationDate;
    this.lastLoginDate = lastLoginDate;
    this.lastCountry = lastCountry;
    this.lastIp = lastIp;
    this.lastCid = lastCid;
    this.languages = languages;
    this.paymentsBlockedTill = paymentsBlockedTill;
    this.emailVerified = emailVerified;
    this.paymentsBlocked = paymentsBlocked;
    this.blocked = blocked;
    this.doNotPay = doNotPay;
    this.ignoreCountry = ignoreCountry;
    this.automaticPayment = automaticPayment;
    this.adsEnabled = adsEnabled;
    this.toolbarUser = toolbarUser;
    this.mobileAppUser = mobileAppUser;
    this.numberOfTransactions = numberOfTransactions;
  }

  public Long getIdExt() {
    return idExt;
  }

  public void setIdExt(Long idExt) {
    this.idExt = idExt;
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

  public Set<TransactionEntity> getTransactions() {
    return transactions;
  }

  public void setTransactions(Set<TransactionEntity> transactions) {
    this.transactions = transactions;
  }

  public Set<Merchant> getMerchants() {
    return merchants;
  }

  public void setMerchants(Set<Merchant> merchants) {
    this.merchants = merchants;
  }

  public void withTransaction(TransactionEntity transaction) {
    if (transactions == null) {
      transactions = new HashSet<>();
    }
    transactions.add(transaction);
  }

  public void withMerchant(Merchant merchant) {
    if (merchants == null) {
      merchants = new HashSet<>();
    }
    merchants.add(merchant);
  }

  // public String toString() {
  // return this.getFullName() + "'s teammates => "
  // + Optional.ofNullable(this.teammates).orElse(Collections.emptySet()).stream()
  // .map(Person::getFullName).collect(Collectors.toList());
  // }

}
