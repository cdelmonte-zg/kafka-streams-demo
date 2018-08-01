package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.util.Date;
import java.util.function.Supplier;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;


abstract class ActorBuilder implements Supplier<ActorBuilder> {
  MockNeat mock;
  ActorType type;
  Long id;
  String email;
  String username;
  String name;
  Date birthdate;
  Date registrationDate;
  Date lastLoginDate;
  String lastCountry;
  String lastIp;
  String lastCid;
  String languages;
  String userAgent;
  int numOfClaims;
  boolean emailVerified;
  boolean paymentsBlocked;
  boolean blocked;
  boolean doNotPay;
  boolean suspect;
  Balance balance;
  BitcoinAccount bitcoinAccount;
  PaypalAccount paypalAccount;
  BankAccount bankAccount;

  ActorBuilder(MockNeat mock) {
    this.mock = mock;
  }

  public void setType(ActorType type) {
    this.type = type;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  public void setLastCountry(String lastCountry) {
    this.lastCountry = lastCountry;
  }

  public void setLastIp(String lastIp) {
    this.lastIp = lastIp;
  }

  public void setLastCid(String lastCid) {
    this.lastCid = lastCid;
  }

  public void setLanguages(String languages) {
    this.languages = languages;
  }

  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }

  public void setEmailVerified(boolean emailVerified) {
    this.emailVerified = emailVerified;
  }

  public void setPaymentsBlocked(boolean paymentsBlocked) {
    this.paymentsBlocked = paymentsBlocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public void setDoNotPay(boolean doNotPay) {
    this.doNotPay = doNotPay;
  }

  public void setSuspect(boolean suspect) {
    this.suspect = suspect;
  }

  public void setNumOfClaims(int numOfClaims) {
    this.numOfClaims = numOfClaims;
  }

  public void setBalance(Balance balance) {
    this.balance = balance;
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

  public abstract Actor build();

  @Override
  public ActorBuilder get() {
    return this;
  }
}
