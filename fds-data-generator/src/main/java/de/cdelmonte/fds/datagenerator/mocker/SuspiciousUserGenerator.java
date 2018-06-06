package de.cdelmonte.fds.datagenerator.mocker;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import de.cdelmonte.fds.datagenerator.container.DataContainer;
import de.cdelmonte.fds.datagenerator.model.Address;
import de.cdelmonte.fds.datagenerator.model.Balance;
import de.cdelmonte.fds.datagenerator.model.User;
import de.cdelmonte.fds.datagenerator.model.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.model.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.model.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class SuspiciousUserGenerator<T> implements GeneratorSupplier<T> {
  private MockNeat mock;
  private List<User> users;

  public SuspiciousUserGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<User> getUsers() {
    return users;
  }

  public List<User> supplyMocks(int howMany) {
    int howManyBankAccounts = 3;
    int howManyBitconsAccounts = 3;
    int howManyPaypalAccounts = 3;
    int howManyAddresses = 3;

    List<Balance> balances = generateBalances(howMany);
    List<Address> addresses = generateAddresses(howManyAddresses);
    MockUnitString browserLanguagesMock = genBrowserLanguage();

    List<BankAccount> bankAccounts = generateBankAccount().list(howManyBankAccounts).val();
    List<PaypalAccount> paypalAccounts = generatePaypalAccount().list(howManyPaypalAccounts).val();
    List<BitcoinAccount> bitcoinAccounts =
        generateBitcoinAccount().list(howManyBitconsAccounts).val();

    List<User> users = mock.reflect(User.class).field("id", mock.longSeq().start(howMany + 1))
        .field("name", mock.names().full()).field("username", mock.users())
        .field("email", mock.emails())
        .field("birthdate",
            mock.localDates().between(LocalDate.of(1945, 01, 01), LocalDate.of(2001, 12, 12))
                .toUtilDate())
        .field("registrationDate",
            mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
        .field("lastCountry", "DE").field("lastIp", mock.ipv4s())
        .field("lastCid", mock.strings().size(12)).field("languages", browserLanguagesMock)
        .field("emailVerified", true).field("paymentsBlocked", false).field("blocked", false)
        .field("doNotPay", false).field("numberOfTransactions", mock.ints().range(13, 20))
        .field("balance", mock.from(balances)).list(howMany).val();

    users.forEach(u -> u.setAddress(addresses.get(new Random().nextInt(howManyAddresses))));

    users.forEach(u -> u.setBankAccount(
        bankAccounts.get(new Random().nextInt(howManyBankAccounts)).setAccountHolder(u.getName())));

    users.forEach(
        u -> u.setPaypalAccount(paypalAccounts.get(new Random().nextInt(howManyPaypalAccounts))
            .setAccountHolder(u.getName()).setAddress(u.getEmail())));

    users.forEach(u -> u
        .setBitcoinAccount(bitcoinAccounts.get(new Random().nextInt(howManyBitconsAccounts))));

    users.forEach((u) -> {
      Calendar cal = Calendar.getInstance();
      cal.setTime(u.getRegistrationDate());
      cal.add(Calendar.DAY_OF_MONTH, new Random().nextInt(29) + 1);
      u.setLastLoginDate(cal.getTime());
    });

    DataContainer.setSuspiciousUsers(users);

    return users;
  }

  private MockUnitString genBrowserLanguage() {
    Supplier<String> browserLanguagesSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.countries().iso2().val()).append(";q=0.9,").append("en-US").append(";q=0.8,")
          .append(mock.countries().iso2().val()).append(";q=0.7,")
          .append(mock.countries().iso2().val()).append(";q=0.6,")
          .append(mock.countries().iso2().val()).append(";q=");

      return buff.toString();
    };

    return () -> browserLanguagesSupplier;
  }

  private List<Address> generateAddresses(int howMany) {
    MockUnitString zipMock = generateZip();
    MockUnitString streetNumberMock = generateStreetNumber();

    List<Address> addresses = mock.reflect(Address.class).field("country", mock.countries().names())
        .field("city", mock.cities().capitals()).field("zipCode", zipMock)
        .field("streetAddress", mock.names()).field("streetNumber", streetNumberMock).list(howMany)
        .val();

    return addresses;
  }

  private MockUnitString generateStreetNumber() {
    Supplier<String> streetNumberSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString streetNumberMock = () -> streetNumberSupplier;
    return streetNumberMock;
  }

  private MockUnitString generateZip() {
    Supplier<String> zipSupplier = () -> {
      StringBuilder buff = new StringBuilder();
      buff.append(mock.chars().letters().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString zipMock = () -> zipSupplier;
    return zipMock;
  }

  private List<Balance> generateBalances(int howMany) {
    List<Balance> balances =
        mock.reflect(Balance.class).field("pending", mock.longs().range(10, 9999999))
            .field("paid", mock.longs().range(1, 9999999))
            .field("denied", mock.longs().range(10, 9999999))
            .field("received", mock.longs().range(10, 9999999)).list(howMany).val();

    return balances;
  }

  private MockUnit<BankAccount> generateBankAccount() {
    Supplier<BankAccount> bankAccountSupplier = () -> {
      String name = mock.names().full().val();
      String[] iBANs = {"fakeIBAN1", "fakeIBAN2", "fakeIBAN3", "fakeIBAN4"};

      BankAccount bankAccount = new BankAccount();
      bankAccount.setIban(iBANs[new Random().nextInt(4)]);
      bankAccount.setBic("ATTFUTDEFF500");
      bankAccount.setAccountHolder(name);

      return bankAccount;
    };

    return () -> bankAccountSupplier;
  }

  private MockUnit<PaypalAccount> generatePaypalAccount() {
    Supplier<PaypalAccount> paypalAccountSupplier = () -> {
      String name = mock.names().full().val();

      PaypalAccount paypalAccount = new PaypalAccount();
      paypalAccount.setAccountHolder(name);
      paypalAccount.setAddress(mock.emails().val());

      return paypalAccount;
    };

    return () -> paypalAccountSupplier;
  }

  private MockUnit<BitcoinAccount> generateBitcoinAccount() {
    Supplier<BitcoinAccount> bitcoinAccountSupplier = () -> {

      String[] addresses = {"fakebitcoinaccount1", "fakebitcoinaccount2", "fakebitcoinaccount3",
          "fakebitcoinaccount4"};

      BitcoinAccount bitcoinAccount = new BitcoinAccount();
      bitcoinAccount.setAddress(addresses[new Random().nextInt(4)]);

      return bitcoinAccount;
    };

    return () -> bitcoinAccountSupplier;
  }
}
