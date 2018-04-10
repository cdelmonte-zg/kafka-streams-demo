package de.cdelmonte.afs.neo4j.events.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import de.cdelmonte.afs.neo4j.service.ImporterService;

@Component
public class KafkaConsumer {
  @Autowired
  ImporterService service;

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "${kafka.topic.import-transactions}")
  public void receive(String payload) {
    logger.debug("received payload='{}'", payload);
    service.importTransactions(payload);
  }
}
