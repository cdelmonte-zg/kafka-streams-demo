package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.util.Date;
import java.util.function.Supplier;

import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.AviosAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;


abstract class ActorBuilder implements Supplier<ActorBuilder> {
  private MockNeat mock;
  private ActorType type;

  private long id;
  private String email;
  private String username;
  private String name;
  private Date birthdate;
  private Gender gender;
  private Role role;

  private boolean avatarSet;
  private boolean active;
  private boolean facebookAccountSet;
  private boolean blocked;
  private boolean subscribedToNewsletter;
  private boolean paymentInfoVerified;
  private boolean paymentBlockedForNotAllowedOperations;
  private boolean paymentBlockedManually;
  private boolean emailAdvertisingAllowed;
  private boolean emailConfirmed;
  private boolean favouriteCategories;
  private long[] favouriteMerchants;
  private long[] ignoredMerchantIds;
  private boolean twoFactorAuthenticationActivated;

  private Date registrationDate;

  private Date lastLoginDate;
  private String lastCountry;
  private String lastIp;
  private String lastCid;
  private String lastUserAgent;
  private boolean suspect;

  private ClientPlatform registrationPlatform;
  private ClientPlatform activationPlatform;


  private Balance balance;
  private BitcoinAccount bitcoinAccount;
  private PaypalAccount paypalAccount;
  private BankAccount bankAccount;
  private AviosAccount aviosAccount;

  private int numberOfTransactions;
  private int numberOfSessions;
  private int numberOfClaims;
  private int numberOfClicks;


  ActorType getType() {
    return type;
  }

  void setType(ActorType type) {
    this.type = type;
  }

  long getId() {
    return id;
  }

  void setId(long id) {
    this.id = id;
  }

  String getEmail() {
    return email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  String getUsername() {
    return username;
  }

  void setUsername(String username) {
    this.username = username;
  }

  String getName() {
    return name;
  }

  void setName(String name) {
    this.name = name;
  }

  Date getBirthdate() {
    return birthdate;
  }

  void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  Gender getGender() {
    return gender;
  }

  void setGender(Gender gender) {
    this.gender = gender;
  }

  Role getRole() {
    return role;
  }

  void setRole(Role role) {
    this.role = role;
  }

  boolean isAvatarSet() {
    return avatarSet;
  }

  void setAvatarSet(boolean avatarSet) {
    this.avatarSet = avatarSet;
  }

  boolean isActive() {
    return active;
  }

  void setActive(boolean active) {
    this.active = active;
  }

  boolean isFacebookAccountSet() {
    return facebookAccountSet;
  }

  void setFacebookAccountSet(boolean facebookAccountSet) {
    this.facebookAccountSet = facebookAccountSet;
  }

  boolean isBlocked() {
    return blocked;
  }

  void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  boolean isSubscribedToNewsletter() {
    return subscribedToNewsletter;
  }

  void setSubscribedToNewsletter(boolean subscribedToNewsletter) {
    this.subscribedToNewsletter = subscribedToNewsletter;
  }

  boolean isPaymentInfoVerified() {
    return paymentInfoVerified;
  }

  void setPaymentInfoVerified(boolean paymentInfoVerified) {
    this.paymentInfoVerified = paymentInfoVerified;
  }


  boolean isPaymentBlockedForNotAllowedOperations() {
    return paymentBlockedForNotAllowedOperations;
  }

  void setPaymentBlockedForNotAllowedOperations(boolean paymentBlockedForNotAllowedOperations) {
    this.paymentBlockedForNotAllowedOperations = paymentBlockedForNotAllowedOperations;
  }

  boolean isPaymentBlockedManually() {
    return paymentBlockedManually;
  }

  void setPaymentBlockedManually(boolean paymentBlockedManually) {
    this.paymentBlockedManually = paymentBlockedManually;
  }

  boolean isEmailAdvertisingAllowed() {
    return emailAdvertisingAllowed;
  }

  void setEmailAdvertisingAllowed(boolean emailAdvertisingAllowed) {
    this.emailAdvertisingAllowed = emailAdvertisingAllowed;
  }

  boolean isEmailConfirmed() {
    return emailConfirmed;
  }

  void setEmailConfirmed(boolean emailConfirmed) {
    this.emailConfirmed = emailConfirmed;
  }

  boolean isFavouriteCategories() {
    return favouriteCategories;
  }

  void setFavouriteCategories(boolean favouriteCategories) {
    this.favouriteCategories = favouriteCategories;
  }

  long[] getFavouriteMerchants() {
    return favouriteMerchants;
  }

  void setFavouriteMerchants(long[] favouriteMerchants) {
    this.favouriteMerchants = favouriteMerchants;
  }

  long[] getIgnoredMerchantIds() {
    return ignoredMerchantIds;
  }

  void setIgnoredMerchantIds(long[] ignoredMerchantIds) {
    this.ignoredMerchantIds = ignoredMerchantIds;
  }

  boolean isTwoFactorAuthenticationActivated() {
    return twoFactorAuthenticationActivated;
  }

  void setTwoFactorAuthenticationActivated(boolean twoFactorAuthenticationActivated) {
    this.twoFactorAuthenticationActivated = twoFactorAuthenticationActivated;
  }

  Date getRegistrationDate() {
    return registrationDate;
  }

  void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  Date getLastLoginDate() {
    return lastLoginDate;
  }

  void setLastLoginDate(Date lastLoginDate) {
    this.lastLoginDate = lastLoginDate;
  }

  String getLastCountry() {
    return lastCountry;
  }

  void setLastCountry(String lastCountry) {
    this.lastCountry = lastCountry;
  }

  String getLastIp() {
    return lastIp;
  }

  void setLastIp(String lastIp) {
    this.lastIp = lastIp;
  }

  String getLastCid() {
    return lastCid;
  }

  void setLastCid(String lastCid) {
    this.lastCid = lastCid;
  }

  String getLastUserAgent() {
    return lastUserAgent;
  }

  void setLastUserAgent(String lastUserAgent) {
    this.lastUserAgent = lastUserAgent;
  }

  boolean isSuspect() {
    return suspect;
  }

  void setSuspect(boolean suspect) {
    this.suspect = suspect;
  }

  ClientPlatform getRegistrationPlatform() {
    return registrationPlatform;
  }

  void setRegistrationPlatform(ClientPlatform registrationPlatform) {
    this.registrationPlatform = registrationPlatform;
  }

  ClientPlatform getActivationPlatform() {
    return activationPlatform;
  }

  void setActivationPlatform(ClientPlatform activationPlatform) {
    this.activationPlatform = activationPlatform;
  }

  Balance getBalance() {
    return balance;
  }

  void setBalance(Balance balance) {
    this.balance = balance;
  }

  BitcoinAccount getBitcoinAccount() {
    return bitcoinAccount;
  }

  void setBitcoinAccount(BitcoinAccount bitcoinAccount) {
    this.bitcoinAccount = bitcoinAccount;
  }

  PaypalAccount getPaypalAccount() {
    return paypalAccount;
  }

  void setPaypalAccount(PaypalAccount paypalAccount) {
    this.paypalAccount = paypalAccount;
  }

  BankAccount getBankAccount() {
    return bankAccount;
  }

  void setBankAccount(BankAccount bankAccount) {
    this.bankAccount = bankAccount;
  }

  AviosAccount getAviosAccount() {
    return aviosAccount;
  }

  public MockNeat getMock() {
    return mock;
  }

  public void setMock(MockNeat mock) {
    this.mock = mock;
  }

  void setAviosAccount(AviosAccount aviosAccount) {
    this.aviosAccount = aviosAccount;
  }

  ActorBuilder(MockNeat mock) {
    this.setMock(mock);
  }

  abstract Actor build();

  @Override
  public ActorBuilder get() {
    return this;
  }

  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

  public void setNumberOfTransactions(int numberOfTransactions) {
    this.numberOfTransactions = numberOfTransactions;
  }

  public int getNumberOfSessions() {
    return numberOfSessions;
  }

  public void setNumberOfSessions(int numberOfSessions) {
    this.numberOfSessions = numberOfSessions;
  }

  public int getNumberOfClaims() {
    return numberOfClaims;
  }

  public void setNumberOfClaims(int numberOfClaims) {
    this.numberOfClaims = numberOfClaims;
  }

  public int getNumberOfClicks() {
    return numberOfClicks;
  }

  public void setNumberOfClicks(int numberOfClicks) {
    this.numberOfClicks = numberOfClicks;
  }
}
