package de.cdelmonte.fds.datagenerator.config;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import de.cdelmonte.fds.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.fds.datagenerator.model.Mock;
import de.cdelmonte.fds.datagenerator.producer.Sender;

@Component
public class JobsConfigurer {
  private static final String SUSPICIOUS_TRANSACTION = "suspiciousTransaction";
  private static final String USER = "user";
  private static final String TRANSACTION = "transaction";
  private static final String SUSPICIOUS_USER = "suspiciousUser";

  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @PostConstruct
  public void generateMocks() {
    generateUsers();
    generateSouspiociusUsers();

    try {
      Thread.sleep(220000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    generateTransactions();
    generateSuspiciousTransactions();
  }

  public void generateUsers() {
    Gson gson = new Gson();;
    List<? extends Mock> users = mocksGenerator.generateMocks(USER, 20);

    for (Mock u : users) {
      messageSender.send("training-users", gson.toJson(u));
    }
  }

  public void generateSouspiociusUsers() {
    Gson gson = new Gson();;

    List<? extends Mock> suspiciousUsers = mocksGenerator.generateMocks(SUSPICIOUS_USER, 20);
    for (Mock u : suspiciousUsers) {
      messageSender.send("training-suspicious-users", gson.toJson(u));
    }
  }

  public void generateTransactions() {
    Gson gson = new Gson();
    int howMany = new Random().nextInt(11) + 1;
    List<? extends Mock> transactions = mocksGenerator.generateMocks(TRANSACTION, howMany);

    for (Mock t : transactions) {
      messageSender.send("training-transactions", gson.toJson(t));
    }
  }

  public void generateSuspiciousTransactions() {
    Gson gson = new Gson();
    int howMany = new Random().nextInt(11) + 1;
    List<? extends Mock> suspiciousTransaction =
        mocksGenerator.generateMocks(SUSPICIOUS_TRANSACTION, howMany);

    for (Mock t : suspiciousTransaction) {
      messageSender.send("training-transactions", gson.toJson(t));
    }
  }
}
