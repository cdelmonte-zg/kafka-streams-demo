package de.cdelmonte.afs.datagenerator.mocker;

import static net.andreinc.mockneat.types.enums.MarkovChainType.KAFKA;
import java.util.List;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import de.cdelmonte.afs.datagenerator.model.Group;
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
        .field("groupId", mock.from(groups).map(Group::getId)).field("email", mock.emails())
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
    System.out.println(text);

    return text;
  }
}
