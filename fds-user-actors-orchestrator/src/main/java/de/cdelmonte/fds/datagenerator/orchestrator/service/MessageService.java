package de.cdelmonte.fds.datagenerator.orchestrator.service;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class MessageService implements Serializable {
  private static final long serialVersionUID = 1L;

  public void sendTransactionToKafka(Event e) {
    Logger.log("Sending transaction to kafka producer");
  }

  public void sendClickToKafka(Event e) {
    String string = e.getModel().getJSON();
    Logger.log("Sending click to kafka producer with json: \n" + string);
  }

  public void sendActorToKafka(Event e) {
    String string = e.getModel().getJSON();
    System.out.println("Sending actor update to kafka producer with json: \n" + string);
  }
}
