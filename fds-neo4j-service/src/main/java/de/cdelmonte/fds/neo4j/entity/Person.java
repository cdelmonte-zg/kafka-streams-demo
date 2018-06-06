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
  private String lastCid;
  private String languages;

  private boolean emailVerified;
  private boolean paymentsBlocked;
  private boolean blocked;
  private boolean doNotPay;
  private int numberOfTransactions;

  private Long balancePending;
  private Long balancePaid;
  private Long balanceDenied;
  private Long balanceReceived;
  private boolean testData;
  private float fraudScore;
  private int[] ratingFactors = new int[] {0, 0, 0, 0, 0, 0};

  @Relationship(type = "PERSON_WITH_TRANSACTION", direction = Relationship.UNDIRECTED)
  public Set<TransactionEntity> transactions;

  @Relationship(type = "PERSON_WITH_MERCHANT", direction = Relationship.UNDIRECTED)
  public Set<Merchant> merchants;

  public Person() {};

  public Long getId() {
    return id;
  }

  public Long getBalancePending() {
    return balancePending;
  }

  public void setBalancePending(Long balancePending) {
    this.balancePending = balancePending;
  }

  public Long getBalancePaid() {
    return balancePaid;
  }

  public void setBalancePaid(Long balancePaid) {
    this.balancePaid = balancePaid;
  }

  public Long getBalanceDenied() {
    return balanceDenied;
  }

  public void setBalanceDenied(Long balanceDenied) {
    this.balanceDenied = balanceDenied;
  }

  public Long getBalanceReceived() {
    return balanceReceived;
  }

  public void setBalanceReceived(Long balanceReceived) {
    this.balanceReceived = balanceReceived;
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

  public boolean isTestData() {
    return testData;
  }

  public void setTestData(boolean testData) {
    this.testData = testData;
  }

  public float getFraudScore() {
    return fraudScore;
  }

  public void setFraudScore(float fraudScore) {
    this.fraudScore = fraudScore;
  }

  public int[] getRatingFactors() {
    return this.ratingFactors;
  }

  public void setRatingFactor(int pos) {
    this.ratingFactors[pos] = 1;
  }

  public void unsetRatingFactor(int pos) {
    this.ratingFactors[pos] = 0;
  }
}
