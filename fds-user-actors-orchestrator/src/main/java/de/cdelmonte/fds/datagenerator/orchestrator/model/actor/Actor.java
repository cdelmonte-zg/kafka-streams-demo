package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.util.Date;
import java.util.function.Supplier;

import javax.swing.Icon;

import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Command;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Context;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Parser;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.PaypalAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class Actor extends Thread implements Supplier<Actor> {
  private ActorType type;
  private Behavior behavior;

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
  private String userAgent;

  private boolean emailVerified;
  private boolean paymentsBlocked;
  private boolean blocked;
  private boolean doNotPay;
  private boolean suspect; // TODO: verify if this property isn't already catch from other fields
  private int numOfTransactions;
  private int numberOfSessions;
  private int numOfClaims;

  private Balance balance;
  private BitcoinAccount bitcoinAccount;
  private PaypalAccount paypalAccount;
  private BankAccount bankAccount;

  private Icon icon;


  private volatile boolean paused = false;


  public Actor(ActorBuilder builder) {
    type = builder.type;
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
    userAgent = builder.userAgent;
    numOfClaims = builder.numOfClaims;
    emailVerified = builder.emailVerified;
    paymentsBlocked = builder.paymentsBlocked;
    blocked = builder.blocked;
    doNotPay = builder.doNotPay;
    suspect = builder.suspect;
    balance = builder.balance;
    bitcoinAccount = builder.bitcoinAccount;
    paypalAccount = builder.paypalAccount;
    bankAccount = builder.bankAccount;

    paused = true;
  }

  public String getFancyName() {
    return name;
  }

  public void setFancyName(String name) {
    this.name = name;
  }

  public void sayHello() {
    Logger.log("\nHey, here is " + name + ", I'm a " + type);
  }

  public Actor setBehavior(Behavior actorBehavior) {
    behavior = actorBehavior;
    return this;
  }

  @Override
  public void run() {
    while (!isInterrupted()) {
      if (!paused) {
        sayHello();

        Context co = new Context();
        co.setActor(this);
        co.setNumberOfClicks(10);
        co.setNumberOfTransactions(20);

        Command expr = Parser.parse(behavior.getProgram());
        expr.interpret(co);

        behavior.executeAlgorithm();
        yield();
      }

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        interrupt();
        Logger.log("bye");
        return;
      }
    }
  }

  public void pause() {
    paused = !paused;

  }

  public boolean isPaused() {
    return paused;
  }

  @Override
  public Actor get() {
    return this;
  }

  public ActorType getType() {
    return type;
  }

  public Behavior getBehavior() {
    return behavior;
  }

  public Long getActorId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public String getUsername() {
    return username;
  }

  public Date getBirthdate() {
    return birthdate;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public Date getLastLoginDate() {
    return lastLoginDate;
  }

  public String getLastCountry() {
    return lastCountry;
  }

  public String getLastIp() {
    return lastIp;
  }

  public String getLastCid() {
    return lastCid;
  }

  public String getLanguages() {
    return languages;
  }

  public String getUserAgent() {
    return userAgent;
  }

  public boolean isEmailVerified() {
    return emailVerified;
  }

  public boolean isPaymentsBlocked() {
    return paymentsBlocked;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public boolean isDoNotPay() {
    return doNotPay;
  }

  public boolean isSuspect() {
    return suspect;
  }

  public int getNumOfTransactions() {
    return numOfTransactions;
  }

  public int getNumberOfSessions() {
    return numberOfSessions;
  }

  public int getNumOfClaims() {
    return numOfClaims;
  }

  public Balance getBalance() {
    return balance;
  }

  public BitcoinAccount getBitcoinAccount() {
    return bitcoinAccount;
  }

  public PaypalAccount getPaypalAccount() {
    return paypalAccount;
  }

  public BankAccount getBankAccount() {
    return bankAccount;
  }

  public void setType(ActorType type) {
    this.type = type;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setUsername(String username) {
    this.username = username;
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

  public void setNumOfTransactions(int numOfTransactions) {
    this.numOfTransactions = numOfTransactions;
  }

  public void setNumberOfSessions(int numberOfSessions) {
    this.numberOfSessions = numberOfSessions;
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

  public void setPaused(boolean paused) {
    this.paused = paused;
  }

  public Icon getIcon() {
    return icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  @Override
  public String toString() {
    return "Actor [type=" + type + "\n behavior=" + behavior + "\n id=" + id + "\n email=" + email
        + "\n username=" + username + "\n name=" + name + "\n birthdate=" + birthdate
        + "\n registrationDate=" + registrationDate + "\n lastLoginDate=" + lastLoginDate
        + "\n lastCountry=" + lastCountry + "\n lastIp=" + lastIp + "\n lastCid=" + lastCid
        + "\n languages=" + languages + "\n userAgent=" + userAgent + "\n emailVerified="
        + emailVerified + "\n paymentsBlocked=" + paymentsBlocked + "\n blocked=" + blocked
        + "\n doNotPay=" + doNotPay + "\n suspect=" + suspect + "\n numOfTransactions="
        + numOfTransactions + "\n numberOfSessions=" + numberOfSessions + "\n numOfClaims="
        + numOfClaims + "\n balance=" + balance + "\n bitcoinAccount=" + bitcoinAccount
        + "\n paypalAccount=" + paypalAccount + "\n bankAccount=" + bankAccount + "\n paused="
        + paused + "]";
  }
}
