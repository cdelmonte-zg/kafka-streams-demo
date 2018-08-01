package de.cdelmonte.fds.datagenerator.orchestrator.service;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;

public class MessageService {

  public void sendTransactionToKafka(Event e) {
    Logger.log("Sending transaction to kafka producer");
  }

  public void sendClickToKafka(Event e) {
    Logger.log("Sending click to kafka producer");
  }
}
