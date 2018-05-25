package de.cdelmonte.afs.datagenerator.mocker;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import de.cdelmonte.afs.datagenerator.container.DataContainer;
import de.cdelmonte.afs.datagenerator.model.Click;
import de.cdelmonte.afs.datagenerator.model.Merchant;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class TransactionGenerator<T> implements GeneratorSupplier<T> {
  public MockNeat mock;

  public TransactionGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<Transaction> supplyMocks(int howMany) {
    List<User> users = DataContainer.getUsers();
    List<Click> clicks = this.generateClicks(howMany);
    List<Merchant> merchants = this.generateMerchants();

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

  private List<Merchant> generateMerchants() {
    int r = new Random().nextInt(1000);

    List<Merchant> merchants = mock.reflect(Merchant.class).field("id", mock.longSeq())
        .field("name", mock.names()).list(r).val();

    return merchants;
  }

  private List<Click> generateClicks(int howMany) {
    List<Click> clicks = mock.reflect(Click.class).field("id", mock.longSeq())
        .field("date",
            mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
        .field("ip", mock.ipv4s()).field("source", mock.names()).list(howMany).val();

    return clicks;
  }
}
