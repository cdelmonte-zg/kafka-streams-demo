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
    GeneratorSupplier<User> su = new UserGenerator<User>();
    GeneratorSupplier<User> u = new SuspiciousUserGenerator<User>();
    GeneratorSupplier<Transaction> t = new TransactionGenerator<Transaction>();
    GeneratorSupplier<Transaction> st = new SuspiciousTransactionGenerator<Transaction>();

    if (type.equals("user"))
      return generate(u, howMany);
    if (type.equals("suspiciousUser"))
      return generate(su, howMany);
    else if (type.equals("transaction"))
      return generate(t, howMany);
    else if (type.equals("suspiciousTransaction"))
      return generate(st, howMany);
    return null;
  }
}
