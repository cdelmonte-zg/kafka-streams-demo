package de.cdelmonte.afs.datagenerator.config;

import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import de.cdelmonte.afs.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.afs.datagenerator.model.Transaction;
import de.cdelmonte.afs.datagenerator.model.User;
import de.cdelmonte.afs.datagenerator.model.payment.BankAccount;
import de.cdelmonte.afs.datagenerator.model.payment.BitcoinAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaymentAccount;
import de.cdelmonte.afs.datagenerator.model.payment.PaypalAccount;
import de.cdelmonte.afs.datagenerator.producer.Sender;

@Component
public class JobsConfigurer {
  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @Scheduled(fixedRateString = "5000")
  public void generateUsers() {

    final RuntimeTypeAdapterFactory<PaymentAccount> paymentAdapter = RuntimeTypeAdapterFactory
        .of(PaymentAccount.class, "type").registerSubtype(BankAccount.class)
        .registerSubtype(PaypalAccount.class).registerSubtype(BitcoinAccount.class);
    final Gson gson = new GsonBuilder().registerTypeAdapterFactory(paymentAdapter).create();

    Random rand = new Random();
    int numberOfUsers = rand.nextInt(1000) + 100;
    List<User> users = mocksGenerator.generateUsers(numberOfUsers);

    for (User u : users) {
      messageSender.send("users", gson.toJson(u));
    }
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
    int t = rand.nextInt(1000) + 100;
    int u = rand.nextInt(1000) + 100;
    int m = rand.nextInt(1000) + 100;
    List<Transaction> transactions = mocksGenerator.generateTransactions(t, u, m);

    for (Transaction transaction : transactions) {
      messageSender.send("transactions", gson.toJson(transaction));
    }
  }
}
