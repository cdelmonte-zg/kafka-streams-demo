package de.cdelmonte.afs.datagenerator.mocker;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.datagenerator.model.Address;
import de.cdelmonte.afs.datagenerator.model.Balance;
import de.cdelmonte.afs.datagenerator.model.Click;
import de.cdelmonte.afs.datagenerator.model.Merchant;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.model.User;
import de.cdelmonte.afs.datagenerator.model.payment.BankAccount;
import de.cdelmonte.afs.datagenerator.model.payment.BitcoinAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaymentAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaypalAccount;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnit;
import net.andreinc.mockneat.abstraction.MockUnitString;
import net.andreinc.mockneat.types.enums.IBANType;

@Service
public class MocksGenerator {

  MockNeat mock;

  public MocksGenerator() {
    this.mock = MockNeat.threadLocal();
  }

  public String generateMocks(int howManyUsers, int howManyGroups) {
    Gson gson = new Gson();
    List<User> users = this.generateUsers(howManyUsers);

    return gson.toJson(users);
  }

  public List<User> generateUsers(int howMany) {
    List<Balance> balances = this.generateBalances(howMany);
    List<Address> addresses = this.generateAddresses(howMany);

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

  public String generateMarkovKafka(int size) {
    MockNeat mock = MockNeat.threadLocal();
    String text = mock.markovs().size(size).type(KAFKA).val();

    return text;
  }

  public List<Transaction> generateTransactions(int howMany, int howManyUsers,
      int howManyMerchants) {
    List<User> users = this.generateUsers(howManyUsers);
    List<Click> clicks = this.generateClicks(howMany);
    List<Merchant> merchants = this.generateMerchants(howManyMerchants);

    Supplier<String> networkNameSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val());
      return buff.toString();
    };
    MockUnitString networkNameMock = () -> networkNameSupplier;

    Supplier<String> cidSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val());
      return buff.toString();
    };
    MockUnitString cidMock = () -> cidSupplier;

    Supplier<String> networkStatusSupplier = () -> {
      String[] statuses = {"pending", "added", "validated", "received", "marked-as-received",
          "denied", "paid", "blocked", "claimed"};
      int indexToReturn = new Random().nextInt(statuses.length);

      return statuses[indexToReturn];
    };
    MockUnitString networkStatusMock = () -> networkStatusSupplier;

    Supplier<String> statusSupplier = () -> {
      String[] statuses = {"pending", "added", "validated", "received", "marked-as-received",
          "denied", "paid", "blocked", "claimed"};
      int indexToReturn = new Random().nextInt(statuses.length);

      return statuses[indexToReturn];
    };
    MockUnitString statusMock = () -> statusSupplier;

    Supplier<String> networkTransactionIdSupplier = () -> {
      StringBuilder buff = new StringBuilder();
      buff.append(mock.chars().alphaNumeric().val()).append(mock.chars().alphaNumeric().val())
          .append(mock.chars().alphaNumeric().val()).append(mock.chars().alphaNumeric().val())
          .append(mock.chars().alphaNumeric().val()).append(mock.chars().alphaNumeric().val())
          .append('c').append(mock.chars().alphaNumeric().val())
          .append(mock.chars().alphaNumeric().val()).append(mock.chars().alphaNumeric().val())
          .append(mock.chars().alphaNumeric().val());

      return buff.toString();
    };
    MockUnitString networkTransactionIdMock = () -> networkTransactionIdSupplier;

    List<Transaction> transactions = mock.reflect(Transaction.class).field("id", mock.longSeq())
        .field("networkName", networkNameMock)
        .field("networkTransactionId", networkTransactionIdMock)
        .field("networkStatus", networkStatusMock)
        .field("date",
            mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
        .field("amount", mock.longs().bound(9999999)).field("commission", mock.longs().bound(99999))
        .field("userCommission", mock.longs().bound(99999)).field("status", statusMock)
        .field("userId", mock.from(users).map(User::getId)).field("click", mock.from(clicks))
        .field("merchant", mock.from(merchants))
        .field("createdAt",
            mock.localDates().between(LocalDate.of(2017, 01, 01), LocalDate.now()).toUtilDate())
        .field("updatedAt",
            mock.localDates().between(LocalDate.of(2017, 01, 01), LocalDate.now()).toUtilDate())
        .field("imported", mock.bools())
        .field("lastImportedAt",
            mock.localDates().between(LocalDate.of(2017, 01, 01), LocalDate.now()).toUtilDate())
        .field("lastCid", cidMock)

        .list(howMany).val();

    return transactions;
  }

  private List<Merchant> generateMerchants(int howMany) {
    List<Merchant> merchants = mock.reflect(Merchant.class).field("id", mock.longSeq())
        .field("name", mock.names()).list(howMany).val();

    return merchants;
  }

  private List<Click> generateClicks(int howMany) {
    List<Click> clicks = mock.reflect(Click.class).field("id", mock.longSeq())
        .field("date",
            mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
        .field("ip", mock.ipv4s()).field("source", mock.names()).list(howMany).val();

    return clicks;
  }

  public String generateTransactionsAsString(int howManyTransactions, int howManyUsers,
      int howManyGroups) {
    Gson gson = new Gson();

    return gson.toJson(generateTransactions(howManyTransactions, howManyUsers, howManyGroups));
  }
}
