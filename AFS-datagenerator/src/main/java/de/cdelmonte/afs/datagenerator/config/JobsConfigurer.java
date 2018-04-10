package de.cdelmonte.afs.datagenerator.config;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import de.cdelmonte.afs.datagenerator.mocker.MocksGenerator;
import de.cdelmonte.afs.datagenerator.producer.Sender;


@Component
public class JobsConfigurer {
  @Autowired
  Sender messageSender;

  @Autowired
  private MocksGenerator mocksGenerator;

  @Scheduled(fixedRateString = "5000")
  public void produceUsers() {
    String mocks = mocksGenerator.generateMocks(1000, 20);
    messageSender.send(mocks, "fakeUsers");
  }

  @Scheduled(fixedRateString = "5000")
  public void produceStrings() {
    Random rand = new Random();
    int r = rand.nextInt(8000) + 1;
    String mocks = mocksGenerator.generateMarkovKafka(r);
    messageSender.send(mocks, "strings");
  }

}
