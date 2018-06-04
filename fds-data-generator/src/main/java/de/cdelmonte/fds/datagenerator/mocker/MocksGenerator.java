package de.cdelmonte.fds.datagenerator.mocker;

import java.util.List;
import org.springframework.stereotype.Service;
import de.cdelmonte.fds.datagenerator.model.Mock;
import de.cdelmonte.fds.datagenerator.model.Transaction;
import de.cdelmonte.fds.datagenerator.model.User;

@Service
public class MocksGenerator {
  private <T> List<? extends Mock> generate(GeneratorSupplier<T> g, int howMany) {
    return g.supplyMocks(howMany);
  }

  public List<? extends Mock> generateMocks(String type, int howMany) {
    GeneratorSupplier<User> g = new UserGenerator<User>();
    GeneratorSupplier<Transaction> t = new TransactionGenerator<Transaction>();
    SuspiciousTransactionGenerator<Transaction> s =
        new SuspiciousTransactionGenerator<Transaction>();

    if (type.equals("user"))
      return generate(g, howMany);
    else if (type.equals("transaction"))
      return generate(t, howMany);
    else if (type.equals("suspiciousTransaction"))
      return generate(s, howMany);
    return null;
  }
}
