package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.time.LocalDate;
import java.time.ZoneId;

import de.cdelmonte.fds.datagenerator.orchestrator.util.MockUtils;
import net.andreinc.mockneat.MockNeat;


public class BadUser extends Actor {
  private static final long serialVersionUID = 1L;

  private BadUser(Builder builder) {
    super(builder);
  }

  public static Builder builder() {
    return new Builder(MockNeat.threadLocal());
  }

  public static final class Builder extends ActorBuilder {
    Builder(MockNeat mock) {
      super(mock);
    }

    public Actor build() {
      setType(ActorType.BAD_USER);
      setId(getMock().longs().range(1, Long.MAX_VALUE).val());
      setName(getMock().names().full().val());

      setEmail(getMock().emails().val());
      setUsername(getMock().users().val());

      setBirthdate(getMock().localDates()
          .between(LocalDate.of(1945, 01, 01), LocalDate.of(2001, 12, 12)).toUtilDate().val());

      setRegistrationDate(getMock().localDates()
          .between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate().val());

      setLastLoginDate(getMock().localDates()
          .between(getRegistrationDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
              LocalDate.now())
          .toUtilDate().val());

      setLastCountry(getMock().ipv4s().val());
      setLastIp(getMock().ipv4s().val());
      setLastCid(getMock().strings().size(12).val());
      setBankAccount(MockUtils.generateBankAccount(getMock()).val());
      setBitcoinAccount(MockUtils.generateBitcoinAccount(getMock()).val());
      setPaypalAccount(MockUtils.generatePaypalAccount(getMock()).val());
      setLastUserAgent(MockUtils.generateUserAgent(getMock()).val());

      setEmailConfirmed(true);
      setPaymentBlockedManually(false);
      setBlocked(false);
      setPaymentBlockedForNotAllowedOperations(false);
      setSuspect(false);
      setNumberOfClaims(getMock().ints().val());

      return new BadUser(this);
    }
  }
}
