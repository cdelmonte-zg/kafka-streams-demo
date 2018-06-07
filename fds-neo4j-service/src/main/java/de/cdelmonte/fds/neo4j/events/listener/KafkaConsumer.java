package de.cdelmonte.fds.neo4j.events.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import de.cdelmonte.fds.neo4j.service.ImporterFactory;
import de.cdelmonte.fds.neo4j.service.importer.PayloadType;

@Component
public class KafkaConsumer {
  @Autowired
  ImporterFactory service;

  private static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

  @KafkaListener(topics = "${kafka.topic.import-training-transactions}")
  public void receive(String payload) {
    logger.debug("received training transactions payload='{}'", payload);
    service.importPayload(PayloadType.TRANSACTION, payload);
  }

  @KafkaListener(topics = "${kafka.topic.import-training-users}")
  public void receiveUsers(String payload) {
    logger.debug("received training users payload='{}'", payload);
    service.importPayload(PayloadType.USER, payload);
  }
}
