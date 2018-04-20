package de.cdelmonte.afs.datagenerator.mocker;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.datagenerator.model.Group;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.model.User;
import net.andreinc.mockneat.MockNeat;

@Service
public class MocksGenerator {

  MockNeat mock;

  public MocksGenerator() {
    this.mock = MockNeat.threadLocal();
  }

  public String generateMocks(int howManyUsers, int howManyGroups) {
    Gson gson = new Gson();
    List<Group> groups = this.generateGroups(howManyGroups);
    List<User> users = this.generateUsers(howManyUsers, groups);

    return gson.toJson(users);
  }

  public List<User> generateUsers(int howManyUsers, List<Group> groups) {
    List<User> users = mock.reflect(User.class).field("id", mock.longSeq())
        .field("firstName", mock.names().first()).field("lastName", mock.names().last())
        .field("email", mock.emails()).field("groupId", mock.from(groups).map(Group::getId))
        .list(howManyUsers).val();

    return users;
  }

  public List<Group> generateGroups(int howManyGroups) {
    List<Group> groups =
        mock.reflect(Group.class).field("id", mock.longSeq().start(100).increment(100))
            .field("name", mock.regex("Group [A-Z]{3}[0-9]{2}")).list(howManyGroups).val();

    return groups;
  }

  public String generateMarkovKafka(int size) {
    MockNeat mock = MockNeat.threadLocal();
    String text = mock.markovs().size(size).type(KAFKA).val();

    return text;
  }

  public List<Transaction> generateTransactions(int howManyTransactions, int howManyUsers,
      int howManyGroups) {
    List<Group> groups = this.generateGroups(howManyGroups);
    List<User> users = this.generateUsers(howManyUsers, groups);

    List<Transaction> transactions =
        mock.reflect(Transaction.class).field("customerId", mock.from(users).map(User::getId))
            .field("firstName", mock.from(users).map(User::getFirstName))
            .field("lastName", mock.from(users).map(User::getLastName))
            .field("creditCardNumber", mock.creditCards().visa())
            .field("itemPurchased", mock.words().nouns()).field("department", mock.departments())
            .field("employeeId", mock.longSeq().start(100).increment(100))
            .field("quantity", mock.ints().range(1, 100))
            .field("price", mock.floats().range(0.1f, 200.2f)).field("purchaseDate", new Date())
            .field("storeId", mock.longSeq().start(100).increment(100))
            .field("zipCode", mock.ints().range(00001, 99999)).list(howManyTransactions).val();

    return transactions;
  }

  public String generateTransactionsAsString(int howManyTransactions, int howManyUsers,
      int howManyGroups) {
    Gson gson = new Gson();

    return gson.toJson(generateTransactions(howManyTransactions, howManyUsers, howManyGroups));
  }
}
