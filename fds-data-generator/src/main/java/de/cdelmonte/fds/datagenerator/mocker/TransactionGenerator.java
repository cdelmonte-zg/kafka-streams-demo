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
import de.cdelmonte.fds.datagenerator.model.Transaction;
import de.cdelmonte.fds.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class TransactionGenerator implements GeneratorSupplier {
  public MockNeat mock;
  Random rand = new Random();

  private long idCounter = 1L;

  public TransactionGenerator() {
    mock = MockNeat.threadLocal();
  }

  public List<Transaction> supplyMocks(int howMany) {
    List<User> users = DataContainer.getUsers();
    MockUnitString statusMock = genStatus();

    List<Transaction> totalTransactions = new LinkedList<Transaction>();

    for (User user : users) {
      int numOfTransactions = user.getNumberOfTransactions();

      List<Transaction> transactions = mock.reflect(Transaction.class)
          .field("id", mock.longSeq().start(idCounter))
          .field("date",
              mock.localDates().between(LocalDate.of(2016, 01, 01), LocalDate.now()).toUtilDate())
          .field("amount", mock.longs().bound(9999999)).field("status", statusMock)
          .field("userId", user.getId()).field("imported", mock.bools()).list(numOfTransactions)
          .val();

      this.idCounter += numOfTransactions;

      transactions.forEach(t -> t.setNetworkName(getNetworkNameFor(t.getMerchant().getName())));

      transactions.forEach((t) -> {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t.getDate());
        cal.add(Calendar.HOUR, rand.nextInt(30) + 1);
        t.setCreatedAt(cal.getTime());
      });

      transactions.forEach((t) -> {
        Calendar cal = Calendar.getInstance();
        cal.setTime(t.getCreatedAt());
        cal.add(Calendar.HOUR, rand.nextInt(300) + 1);
        t.setUpdatedAt(cal.getTime());
      });

      transactions.forEach((t) -> {
        if (t.isImported())
          t.setLastImportedAt(t.getUpdatedAt());
      });

      transactions.forEach(t -> t.setLastCid(user.getLastCid()));


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
      int indexToReturn = rand.nextInt(statuses.length);

      return statuses[indexToReturn];
    };

    return () -> statusSupplier;
  }
}
