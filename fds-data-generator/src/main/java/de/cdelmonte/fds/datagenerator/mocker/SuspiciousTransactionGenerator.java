package de.cdelmonte.fds.datagenerator.mocker;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import de.cdelmonte.fds.datagenerator.container.DataContainer;
import de.cdelmonte.fds.datagenerator.model.Click;
import de.cdelmonte.fds.datagenerator.model.Merchant;
import de.cdelmonte.fds.datagenerator.model.Transaction;
import de.cdelmonte.fds.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class SuspiciousTransactionGenerator<T> implements GeneratorSupplier<T> {
  public MockNeat mock;

  public SuspiciousTransactionGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<Transaction> supplyMocks(int howMany) {
    List<User> users = DataContainer.getUsers();
    List<Click> clicks = this.generateClicks(howMany);
    List<Merchant> merchants = this.generateMerchants();
    MockUnitString networkNameMock = genNetworkName();
    MockUnitString cidMock = genCid();
    MockUnitString statusMock = genStatus();

    List<Transaction> transactions = mock.reflect(Transaction.class).field("id", mock.longSeq())
        .field("networkName", networkNameMock)
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

  private MockUnitString genStatus() {
    Supplier<String> statusSupplier = () -> {
      String[] statuses = {"pending", "added", "validated", "received", "marked-as-received",
          "denied", "paid", "blocked", "claimed"};
      int indexToReturn = new Random().nextInt(statuses.length);

      return statuses[indexToReturn];
    };
    MockUnitString statusMock = () -> statusSupplier;
    return statusMock;
  }

  private MockUnitString genCid() {
    Supplier<String> cidSupplier = () -> {
      StringBuffer buff = new StringBuffer();
      buff.append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val()).append(mock.chars().letters().val())
          .append(mock.chars().letters().val());
      return buff.toString();
    };
    MockUnitString cidMock = () -> cidSupplier;
    return cidMock;
  }

  private MockUnitString genNetworkName() {
    Supplier<String> networkNameSupplier = () -> {
      String[] networks = {"adu_net", "drt_net", "mnt_net", "ptty_net", "fds_net", "tes_net",
          "rdt_net", "ntw_net", "mkr_net", "ote_net", "mmt_net", "tcm_net", "vfn_net", "mon_net"};
      int indexToReturn = new Random().nextInt(networks.length);

      return networks[indexToReturn];
    };
    MockUnitString networkNameMock = () -> networkNameSupplier;
    return networkNameMock;
  }

  private MockUnitString genMerchantName() {
    Supplier<String> merchantNameSupplier = () -> {
      String[] merchants =
          {"amazon", "boohoo", "gymshark", "prettylittlething", "showpo", "athleta", "allsaints",
              "asos", "superdry", "airfrance", "mediamarkt", "telecom", "vodafon", "ebay"};
      int indexToReturn = new Random().nextInt(merchants.length);

      return merchants[indexToReturn];
    };
    MockUnitString merchantNameMock = () -> merchantNameSupplier;
    return merchantNameMock;
  }

  private List<Merchant> generateMerchants() {
    int r = new Random().nextInt(1000);
    MockUnitString merchantNames = genMerchantName();

    List<Merchant> merchants = mock.reflect(Merchant.class).field("id", mock.longSeq())
        .field("name", merchantNames).list(r).val();

    return merchants;
  }

  // TODO: update source with right values
  private List<Click> generateClicks(int howMany) {
    List<Click> clicks = mock.reflect(Click.class).field("id", mock.longSeq())
        .field("date",
            mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
        .field("ip", mock.ipv4s()).field("source", TransactionSource.getRandomSource())
        .list(howMany).val();

    return clicks;
  }
}
