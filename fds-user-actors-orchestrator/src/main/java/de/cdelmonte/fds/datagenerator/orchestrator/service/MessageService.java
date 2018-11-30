package de.cdelmonte.fds.datagenerator.orchestrator.service;

import java.io.Serializable;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Click;
import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;
import de.cdelmonte.fds.datagenerator.orchestrator.model.actor.Actor;
import de.cdelmonte.fds.datagenerator.orchestrator.service.kafka.ClickNotifier;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class MessageService<T extends EventModel> implements Serializable {
  private static final long serialVersionUID = 1L;

  public void sendToKafka(Event<T> event) {
    EventModel model = event.getModel();

    if (model instanceof Actor) {
    } else if (model instanceof Click) {
      new ClickNotifier().notify((Click) model);
    } else if (model instanceof Transaction) {

    }
  }

  private void sendTransactionToKafka(Transaction event) {
    Logger.log("Sending transaction to kafka producer");
  }



  private static void sendActorToKafka(Actor model) {
    String string = model.toString();
    System.out.println("Sending actor update to kafka producer with json: \n" + string);
  }
}
