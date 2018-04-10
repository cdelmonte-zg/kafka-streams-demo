package de.cdelmonte.afs.datagenerator.config;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import de.cdelmonte.afs.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.producer.Sender;

@Component
public class JobsConfigurer {
  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @Scheduled(fixedRateString = "5000")
  public void generateUsers() {
    String mocks = mocksGenerator.generateMocks(1000, 20);
    messageSender.send("fakeUsers", mocks);
  }

  @Scheduled(fixedRateString = "5000")
  public void generateStrings() {
    Random rand = new Random();
    int r = rand.nextInt(8000) + 1;
    String mocks = mocksGenerator.generateMarkovKafka(r);
    messageSender.send("strings", mocks);
  }

  @Scheduled(fixedRateString = "5000")
  public void generateTransactions() {
    Gson gson = new Gson();
    Random rand = new Random();
    int r = rand.nextInt(1000) + 100;
    List<Transaction> transactions = mocksGenerator.generateTransactions(r, 100, 20);

    for (Transaction t : transactions) {
      messageSender.send("transactions", gson.toJson(t));
    }
  }
}
