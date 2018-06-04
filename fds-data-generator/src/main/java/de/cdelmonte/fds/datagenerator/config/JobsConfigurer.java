package de.cdelmonte.fds.datagenerator.config;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import de.cdelmonte.fds.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.fds.datagenerator.model.Mock;
import de.cdelmonte.fds.datagenerator.model.payment.BankAccount;
import de.cdelmonte.fds.datagenerator.model.payment.BitcoinAccount;
import de.cdelmonte.fds.datagenerator.model.payment.PaymentAccount;
import de.cdelmonte.fds.datagenerator.model.payment.PaypalAccount;
import de.cdelmonte.fds.datagenerator.producer.Sender;

@Component
public class JobsConfigurer {
  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @PostConstruct
  public void generateUsers() {
    final RuntimeTypeAdapterFactory<PaymentAccount> paymentAdapter = RuntimeTypeAdapterFactory
        .of(PaymentAccount.class, "type").registerSubtype(BankAccount.class)
        .registerSubtype(PaypalAccount.class).registerSubtype(BitcoinAccount.class);
    final Gson gson = new GsonBuilder().registerTypeAdapterFactory(paymentAdapter).create();

    int howMany = new Random().nextInt(10) + 1;
    List<? extends Mock> users = mocksGenerator.generateMocks("user", howMany);

    for (Mock u : users) {
      messageSender.send("users", gson.toJson(u));
    }
  }

  @Scheduled(fixedRateString = "240000")
  public void generateTransactions() {
    Gson gson = new Gson();
    int howMany = new Random().nextInt(11) + 1;
    List<? extends Mock> transactions = mocksGenerator.generateMocks("transaction", howMany);

    for (Mock t : transactions) {
      messageSender.send("transactions", gson.toJson(t));
    }
  }

  @Scheduled(fixedRateString = "245000")
  public void generateSuspiciousTransactions() {
    Gson gson = new Gson();
    int howMany = new Random().nextInt(11) + 1;
    List<? extends Mock> transactions =
        mocksGenerator.generateMocks("suspiciousTransaction", howMany);

    for (Mock t : transactions) {
      messageSender.send("transactions", gson.toJson(t));
    }
  }
}
