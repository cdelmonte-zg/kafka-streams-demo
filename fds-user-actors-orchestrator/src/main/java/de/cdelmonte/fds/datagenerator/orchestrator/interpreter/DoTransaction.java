package de.cdelmonte.fds.datagenerator.orchestrator.interpreter;

import de.cdelmonte.fds.datagenerator.orchestrator.event.TransactionEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class DoTransaction implements Command {

  @Override
  public void interpret(Context co) {
    Logger.log("Doing good clicks");
    co.notifyObservers(ObservableEventType.TRANSACTION, new TransactionEvent(co.getActor()));
  }
}
