package de.cdelmonte.fds.datagenerator.mocker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.function.Supplier;
import de.cdelmonte.fds.datagenerator.container.DataContainer;
import de.cdelmonte.fds.datagenerator.model.Click;
import de.cdelmonte.fds.datagenerator.model.Merchant;
import de.cdelmonte.fds.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;
import net.andreinc.mockneat.abstraction.MockUnitString;

public class ClickGenerator implements GeneratorSupplier {
  public MockNeat mock;
  List<String> sessionIds;
  Random rand = new Random();

  public ClickGenerator() {
    mock = MockNeat.threadLocal();
    genSessionIds();
  }

  @Override
  public List<Click> supplyMocks(int howMany) {


    List<User> users = DataContainer.getUsers();
    List<Merchant> merchants = this.generateMerchants();


    // clicks can be part of a session.
    // session provider


    // transactions.forEach((t) -> {
    // Click click = new Click();
    // click.setId(rand.nextLong() + 1);
    //
    // Calendar cal = Calendar.getInstance();
    // cal.setTime(t.getDate());
    // cal.add(Calendar.SECOND, (rand.nextInt(600) - 1) * (-1));
    // click.setDate(cal.getTime());
    //
    // click.setIp(user.getLastIp());
    // click.setSource(TransactionSource.getRandomSource());
    //
    // t.setClick(click);
    // });


    return null;
  }

  private void genSessionIds() {
    sessionIds = new ArrayList<String>();
    for (int i = 0; i < 9; i++) {
      sessionIds.add(UUID.randomUUID().toString());
    }
  }

  private MockUnitString genSessionIdMocks() {
    Supplier<String> sessionsSupplier = () -> {
      return sessionIds.get(new Random().nextInt(sessionIds.size()));
    };

    return () -> sessionsSupplier;
  }

  private MockUnitString genMerchantName() {
    Supplier<String> merchantNameSupplier = () -> {
      String[] merchants =
          {"amazon", "boohoo", "gymshark", "prettylittlething", "showpo", "athleta", "allsaints",
              "asos", "superdry", "airfrance", "mediamarkt", "telecom", "vodafon", "ebay"};
      int indexToReturn = rand.nextInt(merchants.length);

      return merchants[indexToReturn];
    };

    return () -> merchantNameSupplier;
  }

  private List<Merchant> generateMerchants() {
    int r = rand.nextInt(1000);
    MockUnitString merchantNames = genMerchantName();

    List<Merchant> merchants = mock.reflect(Merchant.class).field("id", mock.longSeq())
        .field("name", merchantNames).list(r).val();

    return merchants;
  }
}
