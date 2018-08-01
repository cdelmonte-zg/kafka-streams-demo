package de.cdelmonte.fds.datagenerator.orchestrator.model.actor;

import java.time.LocalDate;
import java.time.ZoneId;

import de.cdelmonte.fds.datagenerator.orchestrator.util.MockUtils;
import net.andreinc.mockneat.MockNeat;


public class BadUser extends Actor implements Runnable {
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
      setId(mock.longs().range(1, Long.MAX_VALUE).val());
      setName(mock.names().full().val());

      setEmail(mock.emails().val());
      setUsername(mock.users().val());

      setBirthdate(mock.localDates().between(LocalDate.of(1945, 01, 01), LocalDate.of(2001, 12, 12))
          .toUtilDate().val());

      setRegistrationDate(mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now())
          .toUtilDate().val());

      setLastLoginDate(mock.localDates()
          .between(registrationDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
              LocalDate.now())
          .toUtilDate().val());

      setLastCountry(mock.ipv4s().val());
      setLastIp(mock.ipv4s().val());
      setLastCid(mock.strings().size(12).val());
      setLanguages(MockUtils.generateBrowserLanguage(mock).val());
      setBankAccount(MockUtils.generateBankAccount(mock).val());
      setBitcoinAccount(MockUtils.generateBitcoinAccount(mock).val());
      setPaypalAccount(MockUtils.generatePaypalAccount(mock).val());
      setUserAgent(MockUtils.generateUserAgent(mock).val());

      setEmailVerified(true);
      setPaymentsBlocked(false);
      setBlocked(false);
      setDoNotPay(false);
      setSuspect(false);
      setNumOfClaims(mock.ints().val());

      return new BadUser(this);
    }
  }
}
