package de.cdelmonte.fds.datagenerator.orchestrator.event;

import de.cdelmonte.fds.datagenerator.orchestrator.model.EventModel;
import de.cdelmonte.fds.datagenerator.orchestrator.model.Transaction;


public class TransactionEvent implements Event {
  private Transaction transaction;

  public TransactionEvent(Transaction transaction) {
    this.transaction = transaction;
  }

  @Override
  public EventModel getModel() {
    return transaction;
  }
}
