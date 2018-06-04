package de.cdelmonte.fds.datagenerator.mocker;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Supplier;
import de.cdelmonte.fds.datagenerator.container.DataContainer;
import de.cdelmonte.fds.datagenerator.model.Click;
import de.cdelmonte.fds.datagenerator.model.Merchant;
import de.cdelmonte.fds.datagenerator.model.Transaction;
import de.cdelmonte.fds.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class TransactionGenerator<T> implements GeneratorSupplier<T> {
  public MockNeat mock;

  private long idCounter = 1L;

  public TransactionGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<Transaction> supplyMocks(int howMany) {
    List<User> users = DataContainer.getUsers();
    List<Merchant> merchants = this.generateMerchants();
    MockUnitString statusMock = genStatus();

    List<Transaction> totalTransactions = new LinkedList<Transaction>();

    for (User user : users) {
      int numOfTransactions = 1 + new Random().nextInt(20);

      List<Transaction> transactions = mock.reflect(Transaction.class)
          .field("id", mock.longSeq().start(idCounter))
          .field("date",
              mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
          .field("amount", mock.longs().bound(9999999)).field("status", statusMock)
          .field("userId", user.getId()).field("merchant", mock.from(merchants))
          .field("imported", mock.bools()).list(numOfTransactions).val();

      this.idCounter += numOfTransactions;

      transactions.stream().forEach((t) -> {
        Click click = new Click();
        click.setId(new Random().nextLong() + 1);

        Calendar cal = Calendar.getInstance();
        cal.setTime(t.getDate());
        cal.add(Calendar.SECOND, (new Random().nextInt(600)) * (-1));
        click.setDate(cal.getTime());

        click.setIp(user.getLastIp());
        click.setSource(TransactionSource.getRandomSource());

        t.setClick(click);
      });

      transactions.stream()
          .forEach(t -> t.setNetworkName(getNetworkNameFor(t.getMerchant().getName())));

      transactions.stream().forEach((t) -> {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t.getDate());
        cal.add(Calendar.HOUR, new Random().nextInt(30));
        t.setCreatedAt(cal.getTime());
      });

      transactions.stream().forEach((t) -> {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t.getCreatedAt());
        cal.add(Calendar.HOUR, new Random().nextInt(300));
        t.setUpdatedAt(cal.getTime());
      });

      transactions.stream().forEach((t) -> {
        if (t.isImported())
          t.setLastImportedAt(t.getUpdatedAt());
      });

      transactions.stream().forEach(t -> t.setLastCid(user.getLastCid()));

      Random rand = new Random();
      float commissionPercent = rand.nextFloat();
      float userCommissionPercent = commissionPercent * rand.nextFloat();

      transactions.stream().forEach(
          t -> t.setCommission(Double.valueOf(t.getAmount() * commissionPercent).longValue()));
      transactions.stream().forEach(t -> t.setUserCommission(
          Double.valueOf((t.getAmount() * (userCommissionPercent))).longValue()));

      totalTransactions.addAll(transactions);
    }

    return totalTransactions;
  }

  private String getNetworkNameFor(String merchantName) {
    Map<String, String> merchantsForMetworks = new HashMap<>();

    merchantsForMetworks.put("amazon", "adu_net");
    merchantsForMetworks.put("boohoo", "drt_net");
    merchantsForMetworks.put("gymshark", "mnt_net");
    merchantsForMetworks.put("prettylittlething", "ptty_net");
    merchantsForMetworks.put("showpo", "fds_net");
    merchantsForMetworks.put("athleta", "tes_net");
    merchantsForMetworks.put("allsaints", "rdt_net");
    merchantsForMetworks.put("asos", "ntw_net");
    merchantsForMetworks.put("superdry", "mkr_net");
    merchantsForMetworks.put("airfrance", "ote_net");
    merchantsForMetworks.put("mediamarkt", "mmt_net");
    merchantsForMetworks.put("telecom", "tcm_net");
    merchantsForMetworks.put("vodafon", "vfn_net");
    merchantsForMetworks.put("ebay", "mon_net");

    return merchantsForMetworks.get(merchantName);
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
}
