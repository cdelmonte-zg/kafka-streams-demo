package de.cdelmonte.fds.datagenerator.mocker;

import java.util.List;
import org.springframework.stereotype.Service;
import de.cdelmonte.fds.datagenerator.model.Mock;
import de.cdelmonte.fds.datagenerator.model.MockType;

@Service
public class MocksGenerator {
  private <T> List<? extends Mock> generate(GeneratorSupplier g, int howMany) {
    return g.supplyMocks(howMany);
  }

  public List<? extends Mock> genMocks(MockType type, int howMany) {
    if (type.equals(MockType.USER))
      return generate(new UserGenerator(), howMany);
    if (type.equals(MockType.SUSPICIOUS_USER))
      return generate(new SuspiciousUserGenerator(), howMany);
    else if (type.equals(MockType.TRANSACTION))
      return generate(new TransactionGenerator(), howMany);
    else if (type.equals(MockType.CLICK))
      return generate(new ClickGenerator(), howMany);
    else if (type.equals(MockType.SUSPICIOUS_TRANSACTION))
      return generate(new SuspiciousTransactionGenerator(), howMany);
    return null;
  }
}
