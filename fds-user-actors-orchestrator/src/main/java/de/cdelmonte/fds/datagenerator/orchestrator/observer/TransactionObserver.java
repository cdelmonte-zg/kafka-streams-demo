package de.cdelmonte.fds.datagenerator.orchestrator.observer;

import de.cdelmonte.fds.datagenerator.orchestrator.event.Event;
import de.cdelmonte.fds.datagenerator.orchestrator.service.MessageService;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;

public class TransactionObserver implements Observer {

  MessageService messageService = new MessageService();

  @Override
  public void update(Event e) {
    Logger.log("A transaction is happening now");
    messageService.sendTransactionToKafka(e);
  }
}
