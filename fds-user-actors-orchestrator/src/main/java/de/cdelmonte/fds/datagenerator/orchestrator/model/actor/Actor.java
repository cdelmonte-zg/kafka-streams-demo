package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.function.Supplier;

import javax.swing.Icon;

import de.cdelmonte.fds.datagenerator.orchestrator.behaviors.Behavior;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Command;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Context;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Executor;
import de.cdelmonte.fds.datagenerator.orchestrator.interpreter.Parser;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.AviosAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.payment.PaypalAccount;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ClickObserver;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.TransactionObserver;
import javafx.util.Pair;


public class Actor implements Supplier<Actor>, Serializable {
  private static final long serialVersionUID = 1L;
  private ActorType type;
  private Behavior behavior;
  private volatile boolean paused = false;
  private transient Ghost ghost;
  private Icon icon;

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


  public Actor(ActorBuilder builder) {
    type = builder.getType();
    id = builder.getId();

    email = builder.getEmail();
    username = builder.getUsername();
    name = builder.getName();
    birthdate = builder.getBirthdate();
    gender = builder.getGender();
    role = builder.getRole();
    avatarSet = builder.isAvatarSet();

    active = builder.isActive();
    facebookAccountSet = builder.isFacebookAccountSet();
    blocked = builder.isBlocked();
    subscribedToNewsletter = builder.isSubscribedToNewsletter();
    paymentInfoVerified = builder.isPaymentInfoVerified();
    paymentBlockedForNotAllowedOperations = builder.isPaymentBlockedForNotAllowedOperations();
    paymentBlockedManually = builder.isPaymentBlockedManually();
    emailAdvertisingAllowed = builder.isEmailAdvertisingAllowed();
    emailConfirmed = builder.isEmailConfirmed();
    favouriteCategories = builder.isFavouriteCategories();
    favouriteMerchants = builder.getFavouriteMerchants();
    ignoredMerchantIds = builder.getIgnoredMerchantIds();
    twoFactorAuthenticationActivated = builder.isTwoFactorAuthenticationActivated();


    registrationDate = builder.getRegistrationDate();
    lastLoginDate = builder.getLastLoginDate();
    lastCountry = builder.getLastCountry();
    lastIp = builder.getLastIp();
    lastCid = builder.getLastCid();
    lastUserAgent = builder.getLastUserAgent();

    numberOfClaims = builder.getNumberOfClaims();
    numberOfSessions = builder.getNumberOfSessions();
    numberOfTransactions = builder.getNumberOfTransactions();
    numberOfClicks = builder.getNumberOfClicks();

    suspect = builder.isSuspect();
    balance = builder.getBalance();
    bitcoinAccount = builder.getBitcoinAccount();
    paypalAccount = builder.getPaypalAccount();
    bankAccount = builder.getBankAccount();
    aviosAccount = builder.getAviosAccount();

    registrationPlatform = builder.getRegistrationPlatform();
    activationPlatform = builder.getActivationPlatform();


    paused = true;
  }

  public void start() {
    if (behavior == null) {
      return;
    }

    if (ghost == null) {
      ghost = new Ghost();
    }

    Pair<Context, Command> executor = loadProgram();
    ghost.setProgram(executor.getKey(), executor.getValue());
    ghost.start();
  }

  private Pair<Context, Command> loadProgram() {
    Context co = new Context();
    co.setActor(this);
    co.setNumberOfClicks(10);
    co.setNumberOfTransactions(20);
    co.addObserver(ObservableEventType.CLICK, new ClickObserver());
    co.addObserver(ObservableEventType.TRANSACTION, new TransactionObserver());
    Command executor = new Executor(Parser.parse(behavior.getProgram()));

    return new Pair<Context, Command>(co, executor);
  }

  public String getFancyName() {
    return name;
  }

  public void setFancyName(String name) {
    this.name = name;
  }

  public Actor setBehavior(Behavior actorBehavior) {
    behavior = actorBehavior;
    reloadProgram();

    return this;
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


  public boolean isSuspect() {
    return suspect;
  }

  public int getNumberOfSessions() {
    return numberOfSessions;
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

  public void setSuspect(boolean suspect) {
    this.suspect = suspect;
  }

  public void setNumberOfSessions(int numberOfSessions) {
    this.numberOfSessions = numberOfSessions;
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

  public void pause() {
    ghost.pause();
  }

  public boolean isPaused() {
    return ghost.isPaused();
  }

  public void interrupt() {
    ghost.interrupt();
  }

  public void reloadProgram() {
    if (ghost == null) {
      return;
    }

    Pair<Context, Command> executor = loadProgram();
    ghost.setProgram(executor.getKey(), executor.getValue());
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public ClientPlatform getRegistrationPlatform() {
    return registrationPlatform;
  }

  public void setRegistrationPlatform(ClientPlatform registrationPlatform) {
    this.registrationPlatform = registrationPlatform;
  }

  public ClientPlatform getActivationPlatform() {
    return activationPlatform;
  }

  public void setActivationPlatform(ClientPlatform activationPlatform) {
    this.activationPlatform = activationPlatform;
  }

  public AviosAccount getAviosAccount() {
    return aviosAccount;
  }

  public void setAviosAccount(AviosAccount aviosAccount) {
    this.aviosAccount = aviosAccount;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }

  public boolean isAvatarSet() {
    return avatarSet;
  }

  public void setAvatarSet(boolean avatarSet) {
    this.avatarSet = avatarSet;
  }

  public boolean isFacebookAccountSet() {
    return facebookAccountSet;
  }

  public void setFacebookAccountSet(boolean facebookAccountSet) {
    this.facebookAccountSet = facebookAccountSet;
  }

  public boolean isPaymentBlockedForNotAllowedOperations() {
    return paymentBlockedForNotAllowedOperations;
  }

  public void
      setPaymentBlockedForNotAllowedOperations(boolean paymentBlockedForNotAllowedOperations) {
    this.paymentBlockedForNotAllowedOperations = paymentBlockedForNotAllowedOperations;
  }

  public boolean isEmailAdvertisingAllowed() {
    return emailAdvertisingAllowed;
  }

  public void setEmailAdvertisingAllowed(boolean emailAdvertisingAllowed) {
    this.emailAdvertisingAllowed = emailAdvertisingAllowed;
  }

  public boolean isEmailConfirmed() {
    return emailConfirmed;
  }

  public void setEmailConfirmed(boolean emailConfirmed) {
    this.emailConfirmed = emailConfirmed;
  }

  public long[] getFavouriteMerchants() {
    return favouriteMerchants;
  }

  public void setFavouriteMerchants(long[] favouriteMerchants) {
    this.favouriteMerchants = favouriteMerchants;
  }

  public boolean isFavouriteCategories() {
    return favouriteCategories;
  }

  public void setFavouriteCategories(boolean favouriteCategories) {
    this.favouriteCategories = favouriteCategories;
  }

  public long[] getIgnoredMerchantIds() {
    return ignoredMerchantIds;
  }

  public void setIgnoredMerchantIds(long[] ignoredMerchantIds) {
    this.ignoredMerchantIds = ignoredMerchantIds;
  }

  public boolean isTwoFactorAuthenticationActivated() {
    return twoFactorAuthenticationActivated;
  }

  public void setTwoFactorAuthenticationActivated(boolean twoFactorAuthenticationActivated) {
    this.twoFactorAuthenticationActivated = twoFactorAuthenticationActivated;
  }

  public boolean isPaymentBlockedManually() {
    return paymentBlockedManually;
  }

  public void setPaymentBlockedManually(boolean paymentBlockedManually) {
    this.paymentBlockedManually = paymentBlockedManually;
  }

  public boolean isPaymentInfoVerified() {
    return paymentInfoVerified;
  }

  public void setPaymentInfoVerified(boolean paymentInfoVerified) {
    this.paymentInfoVerified = paymentInfoVerified;
  }

  public String getLastUserAgent() {
    return lastUserAgent;
  }

  public void setLastUserAgent(String lastUserAgent) {
    this.lastUserAgent = lastUserAgent;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }

  public boolean isBlocked() {
    return blocked;
  }

  public void setBlocked(boolean blocked) {
    this.blocked = blocked;
  }

  public boolean isSubscribedToNewsletter() {
    return subscribedToNewsletter;
  }

  public void setSubscribedToNewsletter(boolean subscribedToNewsletter) {
    this.subscribedToNewsletter = subscribedToNewsletter;
  }

  public int getNumberOfTransactions() {
    return numberOfTransactions;
  }

  public void setNumberOfTransactions(int numberOfTransactions) {
    this.numberOfTransactions = numberOfTransactions;
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

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((activationPlatform == null) ? 0 : activationPlatform.hashCode());
    result = prime * result + (active ? 1231 : 1237);
    result = prime * result + (avatarSet ? 1231 : 1237);
    result = prime * result + ((aviosAccount == null) ? 0 : aviosAccount.hashCode());
    result = prime * result + ((balance == null) ? 0 : balance.hashCode());
    result = prime * result + ((bankAccount == null) ? 0 : bankAccount.hashCode());
    result = prime * result + ((behavior == null) ? 0 : behavior.hashCode());
    result = prime * result + ((birthdate == null) ? 0 : birthdate.hashCode());
    result = prime * result + ((bitcoinAccount == null) ? 0 : bitcoinAccount.hashCode());
    result = prime * result + (blocked ? 1231 : 1237);
    result = prime * result + ((email == null) ? 0 : email.hashCode());
    result = prime * result + (emailAdvertisingAllowed ? 1231 : 1237);
    result = prime * result + (emailConfirmed ? 1231 : 1237);
    result = prime * result + (facebookAccountSet ? 1231 : 1237);
    result = prime * result + (favouriteCategories ? 1231 : 1237);
    result = prime * result + Arrays.hashCode(favouriteMerchants);
    result = prime * result + ((gender == null) ? 0 : gender.hashCode());
    result = prime * result + ((icon == null) ? 0 : icon.hashCode());
    result = prime * result + (int) (id ^ (id >>> 32));
    result = prime * result + Arrays.hashCode(ignoredMerchantIds);
    result = prime * result + ((lastCid == null) ? 0 : lastCid.hashCode());
    result = prime * result + ((lastCountry == null) ? 0 : lastCountry.hashCode());
    result = prime * result + ((lastIp == null) ? 0 : lastIp.hashCode());
    result = prime * result + ((lastLoginDate == null) ? 0 : lastLoginDate.hashCode());
    result = prime * result + ((lastUserAgent == null) ? 0 : lastUserAgent.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    result = prime * result + numberOfClaims;
    result = prime * result + numberOfClicks;
    result = prime * result + numberOfSessions;
    result = prime * result + numberOfTransactions;
    result = prime * result + (paused ? 1231 : 1237);
    result = prime * result + (paymentBlockedForNotAllowedOperations ? 1231 : 1237);
    result = prime * result + (paymentBlockedManually ? 1231 : 1237);
    result = prime * result + (paymentInfoVerified ? 1231 : 1237);
    result = prime * result + ((paypalAccount == null) ? 0 : paypalAccount.hashCode());
    result = prime * result + ((registrationDate == null) ? 0 : registrationDate.hashCode());
    result =
        prime * result + ((registrationPlatform == null) ? 0 : registrationPlatform.hashCode());
    result = prime * result + ((role == null) ? 0 : role.hashCode());
    result = prime * result + (subscribedToNewsletter ? 1231 : 1237);
    result = prime * result + (suspect ? 1231 : 1237);
    result = prime * result + (twoFactorAuthenticationActivated ? 1231 : 1237);
    result = prime * result + ((type == null) ? 0 : type.hashCode());
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
    Actor other = (Actor) obj;
    if (activationPlatform != other.activationPlatform)
      return false;
    if (active != other.active)
      return false;
    if (avatarSet != other.avatarSet)
      return false;
    if (aviosAccount == null) {
      if (other.aviosAccount != null)
        return false;
    } else if (!aviosAccount.equals(other.aviosAccount))
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
    if (behavior == null) {
      if (other.behavior != null)
        return false;
    } else if (!behavior.equals(other.behavior))
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
    if (email == null) {
      if (other.email != null)
        return false;
    } else if (!email.equals(other.email))
      return false;
    if (emailAdvertisingAllowed != other.emailAdvertisingAllowed)
      return false;
    if (emailConfirmed != other.emailConfirmed)
      return false;
    if (facebookAccountSet != other.facebookAccountSet)
      return false;
    if (favouriteCategories != other.favouriteCategories)
      return false;
    if (!Arrays.equals(favouriteMerchants, other.favouriteMerchants))
      return false;
    if (gender != other.gender)
      return false;
    if (icon == null) {
      if (other.icon != null)
        return false;
    } else if (!icon.equals(other.icon))
      return false;
    if (id != other.id)
      return false;
    if (!Arrays.equals(ignoredMerchantIds, other.ignoredMerchantIds))
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
    if (lastUserAgent == null) {
      if (other.lastUserAgent != null)
        return false;
    } else if (!lastUserAgent.equals(other.lastUserAgent))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    if (numberOfClaims != other.numberOfClaims)
      return false;
    if (numberOfClicks != other.numberOfClicks)
      return false;
    if (numberOfSessions != other.numberOfSessions)
      return false;
    if (numberOfTransactions != other.numberOfTransactions)
      return false;
    if (paused != other.paused)
      return false;
    if (paymentBlockedForNotAllowedOperations != other.paymentBlockedForNotAllowedOperations)
      return false;
    if (paymentBlockedManually != other.paymentBlockedManually)
      return false;
    if (paymentInfoVerified != other.paymentInfoVerified)
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
    if (registrationPlatform != other.registrationPlatform)
      return false;
    if (role != other.role)
      return false;
    if (subscribedToNewsletter != other.subscribedToNewsletter)
      return false;
    if (suspect != other.suspect)
      return false;
    if (twoFactorAuthenticationActivated != other.twoFactorAuthenticationActivated)
      return false;
    if (type != other.type)
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
    return "Actor [type=" + type + ", behavior=" + behavior + ", paused=" + paused + ", icon="
        + icon + ", id=" + id + ", email=" + email + ", username=" + username + ", name=" + name
        + ", birthdate=" + birthdate + ", gender=" + gender + ", role=" + role + ", avatarSet="
        + avatarSet + ", active=" + active + ", facebookAccountSet=" + facebookAccountSet
        + ", blocked=" + blocked + ", subscribedToNewsletter=" + subscribedToNewsletter
        + ", paymentInfoVerified=" + paymentInfoVerified
        + ", paymentBlockedForNotAllowedOperations=" + paymentBlockedForNotAllowedOperations
        + ", paymentBlockedManually=" + paymentBlockedManually + ", emailAdvertisingAllowed="
        + emailAdvertisingAllowed + ", emailConfirmed=" + emailConfirmed + ", favouriteCategories="
        + favouriteCategories + ", favouriteMerchants=" + Arrays.toString(favouriteMerchants)
        + ", ignoredMerchantIds=" + Arrays.toString(ignoredMerchantIds)
        + ", twoFactorAuthenticationActivated=" + twoFactorAuthenticationActivated
        + ", registrationDate=" + registrationDate + ", lastLoginDate=" + lastLoginDate
        + ", lastCountry=" + lastCountry + ", lastIp=" + lastIp + ", lastCid=" + lastCid
        + ", lastUserAgent=" + lastUserAgent + ", suspect=" + suspect + ", registrationPlatform="
        + registrationPlatform + ", activationPlatform=" + activationPlatform + ", balance="
        + balance + ", bitcoinAccount=" + bitcoinAccount + ", paypalAccount=" + paypalAccount
        + ", bankAccount=" + bankAccount + ", aviosAccount=" + aviosAccount
        + ", numberOfTransactions=" + numberOfTransactions + ", numberOfSessions="
        + numberOfSessions + ", numberOfClaims=" + numberOfClaims + ", numberOfClicks="
        + numberOfClicks + "]";
  }
}
