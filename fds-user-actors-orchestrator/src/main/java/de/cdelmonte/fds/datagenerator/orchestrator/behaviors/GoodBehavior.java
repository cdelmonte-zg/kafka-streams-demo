package de.cdelmonte.fds.datagenerator.orchestrator.behaviors;

import de.cdelmonte.fds.datagenerator.orchestrator.event.ClickEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.event.TransactionEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class GoodBehavior extends Behavior {

  @Override
  public void doClicks() {
    Logger.log("Doing good clicks");
    notifyObservers(ObservableEventType.CLICK, new ClickEvent());
  }

  @Override
  public void doTransactions() {
    Logger.log("Doing good transactions");
    notifyObservers(ObservableEventType.TRANSACTION, new TransactionEvent());
  }
}
