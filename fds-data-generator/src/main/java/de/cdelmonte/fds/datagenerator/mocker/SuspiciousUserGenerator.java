package de.cdelmonte.fds.datagenerator.mocker;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
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
import net.andreinc.mockneat.types.enums.IBANType;

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
    int howManyBankAccounts = 4;
    int howManyBitconsAccounts = 3;
    int howManyPaypalAccounts = 5;
    int howManyAddresses = 4;

    List<Balance> balances = generateBalances(howMany);
    LinkedList<Address> addresses = generateAddresses(howManyAddresses);
    MockUnitString browserLanguagesMock = genBrowserLanguage();

    LinkedList<BankAccount> bankAccounts = (LinkedList<BankAccount>) generateBankAccount()
        .list(LinkedList.class, howManyBankAccounts).val();
    LinkedList<PaypalAccount> paypalAccounts = (LinkedList<PaypalAccount>) generatePaypalAccount()
        .list(LinkedList.class, howManyPaypalAccounts).val();
    LinkedList<BitcoinAccount> bitcoinAccounts =
        (LinkedList<BitcoinAccount>) generateBitcoinAccount()
            .list(LinkedList.class, howManyBitconsAccounts).val();

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

  private LinkedList<Address> generateAddresses(int howMany) {
    Supplier<String> zipSupplier = () -> {
      StringBuilder buff = new StringBuilder();
      buff.append(mock.chars().letters().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val())
          .append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString zipMock = () -> zipSupplier;

    Supplier<String> streetNumberSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().digits().val()).append(mock.chars().digits().val());

      return buff.toString();
    };
    MockUnitString streetNumberMock = () -> streetNumberSupplier;

    Supplier<String> germanCitiesSupplier = () -> {
      List<String> cities = Arrays.asList(new String[] {"Arzberg", "Aschaffenburg", "Aschersleben",
          "Asperg", "Aßlar", "Attendorn", "Aub", "Aue", "Auerbach in der Oberpfalz",
          "Auerbach/Vogtl.", "Augsburg", "Augustusburg", "Aulendorf", "Auma-Weidatal", "Aurich",
          "Babenhausen", "Bacharach", "Backnang", "Bad Aibling", "Bad Arolsen", "Bad Belzig",
          "Bad Bentheim", "Bad Bergzabern", "Bad Berka", "Bad Berleburg",
          "Bad Berneck im Fichtelgebirge", "Bad Bevensen", "Bad Bibra", "Bad Blankenburg",
          "Bad Bramstedt", "Bad Breisig", "Bad Brückenau", "Bad Buchau", "Bad Camberg",
          "Bad Colberg-Heldburg", "Bad Doberan", "Bad Driburg", "Bad Düben", "Bad Dürkheim",
          "Bad Dürrenberg", "Bad Dürrheim", "Bad Elster", "Bad Ems", "Baden-Baden",
          "Bad Fallingbostel", "Bad Frankenhausen/Kyffhäuser", "Bad Freienwalde (Oder)"});

      return cities.get(new Random().nextInt(cities.size()));
    };
    MockUnitString germanCitiesMock = () -> germanCitiesSupplier;

    LinkedList<Address> addresses = mock.reflect(Address.class).field("country", "Germany")
        .field("city", germanCitiesMock).field("zipCode", zipMock)
        .field("streetAddress", mock.names()).field("streetNumber", streetNumberMock)
        .list(LinkedList.class, howMany).val().stream().map(a -> {
          a.setStreetAddress(a.getStreetAddress() + " Strasse");
          return a;
        }).collect(Collectors.toCollection(LinkedList::new));

    return addresses;
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

      BankAccount bankAccount = new BankAccount();
      bankAccount.setIBAN(mock.ibans().type(IBANType.GERMANY).val());
      bankAccount.setBIC("DEUTDEFF500");
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

      BitcoinAccount bitcoinAccount = new BitcoinAccount();
      bitcoinAccount.setAddress(mock.strings().size(24).val());

      return bitcoinAccount;
    };

    return () -> bitcoinAccountSupplier;
  }
}
