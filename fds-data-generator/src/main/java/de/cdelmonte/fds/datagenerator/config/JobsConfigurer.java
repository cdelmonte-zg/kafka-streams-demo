package de.cdelmonte.fds.datagenerator.config;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import de.cdelmonte.fds.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.fds.datagenerator.model.Mock;
import de.cdelmonte.fds.datagenerator.model.MockType;
import de.cdelmonte.fds.datagenerator.producer.Sender;

@Component
public class JobsConfigurer {
  Gson gson = new Gson();
  Random rand = new Random();

  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @PostConstruct
  public void genMocks() {
    genUsers();
    genSouspiociusUsers();

    try {
      Thread.sleep(220000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    genTransactions();
    genSuspiciousTransactions();
  }

  public void genUsers() {
    List<? extends Mock> users = mocksGenerator.genMocks(MockType.USER, 20);

    for (Mock u : users) {
      messageSender.send("training-users", gson.toJson(u));
    }
  }

  public void genSouspiociusUsers() {
    List<? extends Mock> suspiciousUsers = mocksGenerator.genMocks(MockType.SUSPICIOUS_USER, 20);
    for (Mock u : suspiciousUsers) {
      messageSender.send("training-suspicious-users", gson.toJson(u));
    }
  }

  public void genClicks() {
    List<? extends Mock> suspiciousUsers =
        mocksGenerator.genMocks(MockType.CLICK, rand.nextInt(20) + 1);
    for (Mock u : suspiciousUsers) {
      messageSender.send("training-suspicious-users", gson.toJson(u));
    }
  }

  public void genTransactions() {
    List<? extends Mock> transactions =
        mocksGenerator.genMocks(MockType.TRANSACTION, rand.nextInt(11) + 1);

    for (Mock t : transactions) {
      messageSender.send("training-transactions", gson.toJson(t));
    }
  }

  public void genSuspiciousTransactions() {
    int howMany = rand.nextInt(11) + 1;
    List<? extends Mock> suspiciousTransaction =
        mocksGenerator.genMocks(MockType.SUSPICIOUS_TRANSACTION, howMany);

    for (Mock t : suspiciousTransaction) {
      messageSender.send("training-transactions", gson.toJson(t));
    }
  }
}
