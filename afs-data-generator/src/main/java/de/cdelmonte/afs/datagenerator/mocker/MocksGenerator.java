package de.cdelmonte.afs.datagenerator.mocker;

import java.util.List;
import org.springframework.stereotype.Service;
import de.cdelmonte.afs.datagenerator.model.Mock;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.model.User;

@Service
public class MocksGenerator {
  public GeneratorSupplier<User> g = new UserGenerator<User>();
  public GeneratorSupplier<Transaction> t = new TransactionGenerator<Transaction>();

  public <T> List<? extends Mock> generate(GeneratorSupplier<T> g, int howMany) {
    return g.supplyMocks(howMany);
  }

  public List<? extends Mock> generateMocks(String type, int howMany) {
    switch (type) {
      case "user":
        return generate(g, howMany);
      case "transaction":
        return generate(t, howMany);
    }
    return null;
  }
}
