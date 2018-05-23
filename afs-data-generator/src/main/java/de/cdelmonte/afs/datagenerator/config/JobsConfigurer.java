package de.cdelmonte.afs.datagenerator.config;

import java.util.List;
import java.util.Random;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import de.cdelmonte.afs.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.afs.datagenerator.model.Mock;
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

  @PostConstruct
  public void generateUsers() {
    final RuntimeTypeAdapterFactory<PaymentAccount> paymentAdapter = RuntimeTypeAdapterFactory
        .of(PaymentAccount.class, "type").registerSubtype(BankAccount.class)
        .registerSubtype(PaypalAccount.class).registerSubtype(BitcoinAccount.class);
    final Gson gson = new GsonBuilder().registerTypeAdapterFactory(paymentAdapter).create();

    Random rand = new Random();
    int howMany = rand.nextInt(1000) + 100;
    List<? extends Mock> users = mocksGenerator.generateMocks("user", howMany);

    for (Mock u : users) {
      messageSender.send("users", gson.toJson(u));
    }
  }

  @Scheduled(fixedRateString = "60000")
  public void generateTransactions() {
    Gson gson = new Gson();
    int howMany = new Random().nextInt(1000) + 100;
    List<? extends Mock> transactions = mocksGenerator.generateMocks("transaction", howMany);

    for (Mock transaction : transactions) {
      messageSender.send("transactions", gson.toJson(transaction));
    }
  }
}
