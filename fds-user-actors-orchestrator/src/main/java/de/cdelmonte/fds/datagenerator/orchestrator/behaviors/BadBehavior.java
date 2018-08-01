package de.cdelmonte.fds.datagenerator.orchestrator.behaviors;

import de.cdelmonte.fds.datagenerator.orchestrator.event.ClickEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.event.TransactionEvent;
import de.cdelmonte.fds.datagenerator.orchestrator.observer.ObservableEventType;
import de.cdelmonte.fds.datagenerator.orchestrator.util.logging.Logger;


public class BadBehavior extends Behavior {
  @Override
  public void doClicks() {
    Logger.log("Doing bad clicks");
    notifyObservers(ObservableEventType.CLICK, new ClickEvent());
  }

  @Override
  public void doTransactions() {
    Logger.log("Doing bad transactions");
    notifyObservers(ObservableEventType.TRANSACTION, new TransactionEvent());
  }
}
