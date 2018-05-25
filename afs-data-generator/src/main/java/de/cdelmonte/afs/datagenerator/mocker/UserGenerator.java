package de.cdelmonte.afs.datagenerator.mocker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import de.cdelmonte.afs.datagenerator.container.DataContainer;
import de.cdelmonte.afs.datagenerator.model.Address;
import de.cdelmonte.afs.datagenerator.model.Balance;
import de.cdelmonte.afs.datagenerator.model.User;
import de.cdelmonte.afs.datagenerator.model.payment.BankAccount;
import de.cdelmonte.afs.datagenerator.model.payment.BitcoinAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaymentAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.IBANType;

public class UserGenerator<T> implements GeneratorSupplier<T> {
  private MockNeat mock;
  private List<User> users;

  public UserGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<User> getUsers() {
    return users;
  }

  public List<User> supplyMocks(int howMany) {
    List<Balance> balances = generateBalances(howMany);
    List<Address> addresses = generateAddresses(howMany);

    Supplier<String> browserLanguagesSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append("de-DE,de").append(";q=0.9,").append("en-US").append(";q=0.8,")
          .append(mock.countries().iso2().val()).append(";q=0.7,")
          .append(mock.countries().iso2().val()).append(";q=0.6,")
          .append(mock.countries().iso2().val()).append(";q=");

      return buff.toString();
    };
    MockUnitString browserLanguagesMock = () -> browserLanguagesSupplier;

    Supplier<List<PaymentAccount>> paymentAccountsSupplier = () -> {
      List<PaymentAccount> accounts = new ArrayList<>();
      String name = mock.names().full().val();

      BankAccount bankAccount = new BankAccount();
      bankAccount.setIBAN(mock.ibans().type(IBANType.GERMANY).val());
      bankAccount.setBIC("DEUTDEFF500");
      bankAccount.setAccountHolder(name);
      accounts.add(bankAccount);

      BitcoinAccount bitcoinAccount = new BitcoinAccount();
      bitcoinAccount.setAddress(mock.strings().size(24).val());
      accounts.add(bitcoinAccount);

      PaypalAccount paypalAccount = new PaypalAccount();
      paypalAccount.setAccountHolder(name);
      paypalAccount.setAddress(mock.emails().val());
      accounts.add(paypalAccount);

      return accounts;
    };
    MockUnit<List<PaymentAccount>> paymentAccounts = () -> paymentAccountsSupplier;

    List<User> users =
        mock.reflect(User.class).field("id", mock.longSeq()).field("name", mock.names().full())
            .field("username", mock.users()).field("email", mock.emails())

            .field("birthdate",
                mock.localDates().between(LocalDate.of(1945, 01, 01), LocalDate.of(2001, 12, 12))
                    .toUtilDate())
            .field("registrationDate",
                mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
            .field("lastLoginDate",
                mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())

            .field("lastCountry", mock.countries().iso2()).field("lastIp", mock.ipv4s())
            .field("lastCid", mock.longs()).field("languages", browserLanguagesMock)
            .field("emailVerified", mock.bools()).field("paymentsBlocked", mock.bools())
            .field("blocked", mock.bools()).field("doNotPay", mock.bools())
            .field("ignoreCountry", mock.bools()).field("automaticPayment", mock.bools())

            .field("adsEnabled", mock.bools()).field("toolbarUser", mock.bools())
            .field("mobileAppUser", mock.bools()).field("numberOfTransactions", mock.ints())

            .field("balance", mock.from(balances)).field("address", mock.from(addresses))
            .field("paymentAccounts", paymentAccounts).list(howMany).val();

    DataContainer.setUsers(users);

    return users;
  }

  private List<Address> generateAddresses(int howMany) {
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

    List<Address> addresses = mock.reflect(Address.class).field("country", mock.countries().names())
        .field("city", mock.cities().capitals()).field("zipCode", zipMock)
        .field("streetAddress", mock.names()).field("streetNumber", streetNumberMock).list(howMany)
        .val();

    return addresses;
  }

  private List<Balance> generateBalances(int howMany) {
    List<Balance> balances =
        mock.reflect(Balance.class).field("pending", mock.longs()).field("paid", mock.longs())
            .field("denied", mock.longs()).field("received", mock.longs()).list(howMany).val();

    return balances;
  }
}
